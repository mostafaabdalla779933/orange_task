package com.example.detials

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.core.model.MovieModel
import com.example.core.network.Constant
import com.example.detials.databinding.FragmentSecondBinding
import com.google.gson.Gson


class SecondFragment : Fragment() {

    lateinit var binding: FragmentSecondBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        arguments?.let { bundle ->
            bundle.getString("movie").let { artStr ->
                    binding.apply {
                        txt.text = artStr
                    }
            }
        }

        return binding.root
    }


}