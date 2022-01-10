package com.example.orange_task.di.component

import androidx.lifecycle.ViewModelProvider
import com.example.core.di.module.NetworkModule
import com.example.core.network.ApiService
import com.example.core.di.module.AppModule
import com.example.core.di.module.DISPATCHER_IO
import com.example.core.di.module.DISPATCHER_MAIN_THREAD
import com.example.orange_task.di.viewModel.ViewModelModule
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
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

   // fun getSchedulers() : SchedulersProvider

    @Named(DISPATCHER_MAIN_THREAD)
    fun getMainThread(): CoroutineDispatcher

    @Named(DISPATCHER_IO)
    fun getIOThread():CoroutineDispatcher

}


