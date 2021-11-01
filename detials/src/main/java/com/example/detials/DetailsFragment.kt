package com.example.detials

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.core.model.MovieModel
import com.example.core.network.Constant
import com.example.detials.databinding.FragmentDetialsBinding
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.JsonReader
import java.io.StringReader

class DetailsFragment : Fragment() {


    lateinit var binding: FragmentDetialsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetialsBinding.inflate(layoutInflater)


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