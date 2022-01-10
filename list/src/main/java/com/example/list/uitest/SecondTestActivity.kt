package com.example.list.uitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.list.databinding.ActivitySecondTestBinding

class SecondTestActivity : AppCompatActivity() {
    lateinit var binding:ActivitySecondTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            finish()
        }
    }
}