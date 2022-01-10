package com.example.list.uitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.list.databinding.ActivityFirstTestBinding

class FirstTestActivity : AppCompatActivity() {
    lateinit var binding: ActivityFirstTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            startActivity(Intent(this,SecondTestActivity::class.java))
        }
    }
}