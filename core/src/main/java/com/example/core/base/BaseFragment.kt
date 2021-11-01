package com.example.core.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.core.di.viewModel.ViewModelFactory
import javax.inject.Inject


abstract class BaseFragment<V : ViewBinding, VM : BaseViewModel>
    : Fragment(){


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: V? = null
    val binding get() = _binding!!

    open  var viewModel: VM? =null

    open lateinit var progressDialog: ProgressDialog

    abstract fun initBinding(): V

    abstract fun initViewModel()

    abstract fun onFragmentCreated()


    override fun onAttach(context: Context) {
        super.onAttach(context)

        injectFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreate(savedInstanceState)

        initViewModel()
        _binding = initBinding()

        if (context != null) progressDialog = ProgressDialog(requireContext())

        viewModel?.command?.observe(viewLifecycleOwner){
            hideLoading()
            onCommandChange(it)
        }



        onFragmentCreated()

        return binding.root
    }

    abstract fun onCommandChange(command: BaseCommand?)

    abstract fun injectFragment()

    fun showLoading() {
        progressDialog.show()
    }

    fun hideLoading() {
        progressDialog.dismiss()
    }

    override fun onDestroyView() {
        hideLoading()
        _binding = null
        super.onDestroyView()
    }

    fun showSuccessMsg(msg: String) {
        if (context != null)
            Toast.makeText(
                requireContext(),
                HtmlCompat.fromHtml(
                    "<font color='green'>$msg</font>",
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                ),
                Toast.LENGTH_LONG
            ).show()
    }

    fun showErrorMsg(msg: String) {
        if (context != null)
            Toast.makeText(
                requireContext(),
                HtmlCompat.fromHtml(
                    "<font color='red'>$msg</font>",
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                ),
                Toast.LENGTH_LONG
            ).show()
    }

}