package com.example.list.ui.movies



import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.core.base.BaseCommand
import com.example.core.base.BaseFragment
import com.example.list.R
import com.example.list.databinding.FragmentListBinding
import com.example.list.ui.host.HostActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ListFragment : BaseFragment<FragmentListBinding,MoviesViewModel>(){

    lateinit var movieAdapter: MovieAdapter

    override fun injectFragment() {

        (requireActivity() as HostActivity).listComponent.inject(this)
    }

    override fun initBinding(): FragmentListBinding {
        return  FragmentListBinding.inflate(layoutInflater)
    }


    override fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MoviesViewModel::class.java)
    }


    override fun onFragmentCreated() {
        movieAdapter = MovieAdapter { movie ->

            movieAdapter.currentList.let{
                it.toMutableList().let{list->
                    list.remove(movie)
                    movieAdapter.submitList(list)
                }
            }
//            findNavController().navigate(
//                    "android-app://example.com/details/${Gson().toJson(movie)}".toUri(),
//                    NavOptions.Builder()
//                        .setEnterAnim(R.anim.slide_in_right)
//                        .setExitAnim(R.anim.slide_out_left)
//                        .setPopEnterAnim(R.anim.slide_in_left)
//                        .setPopExitAnim(R.anim.slide_out_right)
//                        .build()
//            )
        }

        binding.rvNews.apply {
            adapter = movieAdapter
        }


        fetchData()

        lifecycleScope.launch {
            viewModel?.state?.collect {
                render(it)
            }
        }
    }


    fun render(state:ListViewState){
        when(state){
            is ListViewState.MovieList ->{
                hideLoading()
                movieAdapter.submitList(state.list)
            }
        }
    }

    override fun onCommandChange(command: BaseCommand?) {
        when(command){
            is BaseCommand.Success ->{
                showSuccessMsg(command.successMessage)
            }
            is BaseCommand.Error ->{
                showErrorMsg(command.errorString)
                showReload()
            }
        }
    }

    private fun showReload(){
        Snackbar.make(binding.root ,"connection failed", Snackbar.LENGTH_INDEFINITE )
            .setAction("reload") {
                fetchData()
            }.show()

    }


    private fun fetchData(){
        showLoading()
        lifecycleScope.launch {
            viewModel?.channel?.send(ListIntent.GetList)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel?.onClear()
    }

}