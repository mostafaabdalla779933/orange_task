package com.example.detials

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.detials.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }


}