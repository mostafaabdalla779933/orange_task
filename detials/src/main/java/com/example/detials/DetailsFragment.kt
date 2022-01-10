package com.example.detials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import com.bumptech.glide.Glide
import com.example.core.model.MovieModel
import com.example.core.network.Constant
import com.example.detials.databinding.FragmentDetialsBinding
import com.google.gson.Gson


class DetailsFragment : Fragment() {


    lateinit var binding: FragmentDetialsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetialsBinding.inflate(layoutInflater)



       ViewCompat.setTransitionName(binding.ivMovie, getString(R.string.img))

        sharedElementEnterTransition = ChangeBounds().apply {
            duration = 750
        }
//        sharedElementReturnTransition= ChangeBounds().apply {
//            duration = 750
//        }




        displayDetails()
        return binding.root
    }


    private fun displayDetails() {
        arguments?.let { bundle ->
            (bundle.get("movie") as String).let { artStr ->
                try {
                    Gson().fromJson(artStr, MovieModel::class.java).let{ movie ->
                        binding.apply {
                            tvContent.text = movie.overview
                            tvTitle.text = movie.title
                            tvDate.text = movie.releaseDate

                            Glide.with(requireContext())
                                .load(Constant.TMDB_IMAGEURL+movie.posterPath)
                                .into(ivMovie)
                        }
                    }
                }catch (e:Exception){

                }

            }
        }
    }


}