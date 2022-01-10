package com.example.orange_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.orange_task.databinding.ActivityMostafaBinding

class MostafaActivity : AppCompatActivity() {
    lateinit var binding: ActivityMostafaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostafaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.let {

            binding.txt.text = it.getStringExtra("title")

        }
    }


    companion object{
        const val TYPE ="type"
        const val LINK ="link"
        const val WEB = "web"
        const val DEEP = "deep"
    }
}