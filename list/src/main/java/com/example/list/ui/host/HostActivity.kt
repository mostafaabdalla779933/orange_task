package com.example.list.ui.host

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.core.app.MovieApplication
import com.example.core.network.Constant
import com.example.list.R
import com.example.list.databinding.ActivityTestBinding
import com.example.list.di.component.DaggerListComponent


import com.example.list.di.component.ListComponent
import com.example.list.ui.movies.getLoading

class HostActivity : AppCompatActivity() {


   lateinit var listComponent: ListComponent
   lateinit var binding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        listComponent = DaggerListComponent.builder()
            .appComponent((application as MovieApplication).appComponent).build()
        listComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding =ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Glide.with(this)
            .load("https://i.pinimg.com/originals/56/6f/66/566f663b0f6a6973d5394e1b53af576f.jpg")
            .error(R.drawable.ic_back)
            .into(binding.imgHomeParallax)


    }

}