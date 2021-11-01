package com.example.core.app

import android.app.Application
import com.example.orange_task.di.component.AppComponent
import com.example.orange_task.di.component.DaggerAppComponent


class MovieApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

       appComponent = DaggerAppComponent.create()

    }
}