package com.example.list.ui.movies

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.View.MeasureSpec
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.core.base.BaseCommand
import com.example.core.base.BaseFragment
import com.example.core.model.MovieModel
import com.example.list.R
import com.example.list.databinding.FragmentListBinding
import com.example.list.ui.host.HostActivity
import com.example.list.uitest.FirstTestActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ListFragment : BaseFragment<FragmentListBinding, MoviesViewModel>() {

    lateinit var movieAdapter: MovieAdapter

    override fun injectFragment() {

        (requireActivity() as HostActivity).listComponent.inject(this)
    }

    override fun initBinding(): FragmentListBinding {
        return FragmentListBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MoviesViewModel::class.java)
    }

    override fun onFragmentCreated() {
        movieAdapter = MovieAdapter { movie ,view,position,txt->

            when(position){
                0-> {
                   // txt.visibility =  if(txt.visibility == View.VISIBLE) View.GONE else View.VISIBLE
                    doAnimation(txt,movie.visiblity)
                }
                1->navigateToFragment(movie,view)
                2->navigateToActivity(movie.title?:"",view)
                3->navigate(movie)
                4->navigate()
                5->{startActivity(Intent(requireContext(), FirstTestActivity::class.java))}
            }
        }

        binding.rvNews.apply {
            adapter = movieAdapter
        }

        sharedElementReturnTransition =  TransitionInflater.from(this.context).inflateTransition(android.R.transition.fade)

        postponeEnterTransition()
        binding.rvNews.doOnPreDraw {
            startPostponedEnterTransition()
        }

        fetchData()
        lifecycleScope.launch {
            viewModel?.state?.collect {
                render(it)
            }
        }
    }

    private fun render(state: ListViewState) {
        when (state) {
            is ListViewState.MovieList -> {
                hideLoading()
                movieAdapter.submitList(state.list)
            }
        }
    }

    override fun onCommandChange(command: BaseCommand?) {
        when (command) {
            is BaseCommand.Success -> {
                showSuccessMsg(command.successMessage)
            }
            is BaseCommand.Error -> {
                showErrorMsg(command.errorString)
                showReload()
            }
        }
    }

    private fun showReload() {
        Snackbar.make(binding.root, "connection failed", Snackbar.LENGTH_INDEFINITE)
            .setAction("reload") {
                fetchData()
            }.show()
    }

    private fun doAnimation(txt:View,visi:Boolean){
//        val slideAnimator  =if(txt.visibility == View.VISIBLE){
//            ValueAnimator.ofInt(txt.height, 0).setDuration(300)
//        }else{
//           // txt.visibility = View.VISIBLE
//            txt.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
//            Log.i("main", "doAnimation: ${txt.measuredHeight}   ${txt.height}")
//            ValueAnimator.ofInt(0, txt.measuredHeight).setDuration(1000)
//        }

        val slideAnimator  =if(visi){
            ValueAnimator.ofInt(txt.height, 0).setDuration(300)
        }else{
            txt.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
            Log.i("main", "doAnimation: ${txt.measuredHeight}   ${txt.height}")
            ValueAnimator.ofInt(0, txt.measuredHeight).setDuration(1000)
        }






        slideAnimator.addUpdateListener{ animation ->
            Log.i("main", "doAnimation: ${animation.animatedValue as Int}")
            txt.layoutParams.height = animation.animatedValue as Int
            txt.requestLayout()
        }


//        slideAnimator.addListener(object :AnimatorListener{
//            override fun onAnimationStart(p0: Animator?) {}
//            override fun onAnimationEnd(p0: Animator?) {
//               // txt.visibility =  if(txt.visibility == View.VISIBLE) View.GONE else View.VISIBLE
//
//                if(txt.visibility == View.VISIBLE){
//                    txt.visibility =View.GONE
//                }
//            }
//            override fun onAnimationCancel(p0: Animator?) {}
//            override fun onAnimationRepeat(p0: Animator?) {}
//
//        })
        val set = AnimatorSet()
        set.play(slideAnimator)
        set.interpolator = AccelerateDecelerateInterpolator()
        set.start()
    }

    private fun fetchData() {
        showLoading()
        lifecycleScope.launch {
            viewModel?.channel?.send(ListIntent.GetList)
        }
    }


    private fun navigate(movie: MovieModel) {
        findNavController().navigate(
            "android-app://example.com/details/${Gson().toJson(movie)}".toUri(),
            NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .build()
        )
    }

    fun navigate() {
        findNavController().navigate(
            "android-app://example.com/second/https://www.google.com/".toUri(),
            NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .build()
        )
    }

    fun navigateToFragment(movie: MovieModel,view:View){
        findNavController().navigate(
            "android-app://example.com/details/${Gson().toJson(movie)}".toUri(),
            null,
            FragmentNavigatorExtras(view to getString(R.string.img))
        )
    }

    fun navigateToActivity(title: String,view:View) {
        val intent = Intent()
        intent.setClassName(requireContext(),"com.example.orange_task.MostafaActivity")
        intent.putExtra("title",title)
        val transitionActivityOptions: ActivityOptions =
            ActivityOptions.makeSceneTransitionAnimation(
                context as AppCompatActivity?,
                view, getString(R.string.tras)
            )
        requireActivity().startActivity(intent,transitionActivityOptions.toBundle())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel?.onClear()
    }

}