package com.example.orange_task.di.component

import androidx.lifecycle.ViewModelProvider
import com.example.core.di.module.NetworkModule
import com.example.core.network.ApiService
import com.example.core.di.module.AppModule
import com.example.orange_task.di.scheduler.SchedulersProvider
import com.example.orange_task.di.viewModel.ViewModelModule
import dagger.Component
import javax.inject.Singleton





@Singleton
@Component(
    modules = [
        NetworkModule::class,
        AppModule::class,
        ViewModelModule::class
        ]
)

interface AppComponent  {

    fun getApiService(): ApiService

    fun getViewModelFactory(): ViewModelProvider.Factory

    fun getSchedulers() : SchedulersProvider

}


