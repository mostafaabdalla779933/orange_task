package com.example.list.ui.host

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.core.app.MovieApplication
import com.example.list.R
import com.example.list.di.component.DaggerListComponent


import com.example.list.di.component.ListComponent

class HostActivity : AppCompatActivity() {


   lateinit var listComponent: ListComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        listComponent = DaggerListComponent.builder()
            .appComponent((application as MovieApplication).appComponent).build()
        listComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

    }

}