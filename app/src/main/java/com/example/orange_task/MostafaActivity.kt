package com.example.orange_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MostafaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostafa)
    }


    companion object{
        const val TYPE ="type"
        const val LINK ="link"
        const val WEB = "web"
        const val DEEP = "deep"
    }
}