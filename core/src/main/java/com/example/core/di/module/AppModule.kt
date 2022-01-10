package com.example.core.di.module

import android.app.Application
import android.content.Context
import com.example.core.di.scheduler.SchedulersFacade
import com.example.orange_task.di.scheduler.SchedulersProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

const val DISPATCHER_MAIN_THREAD = "mainThread"
const val DISPATCHER_IO = "io"

@Module
class AppModule {

//    @Binds
//    abstract fun bindContext(application: Application): Context
//
//    @Binds
//    abstract fun providerScheduler(schedulersFacade: SchedulersFacade): SchedulersProvider

    @Provides
    @Named(DISPATCHER_MAIN_THREAD)
    fun provideAndroidMainThreadDispatcher() : CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Named(DISPATCHER_IO)
    fun provideDispatcherIO() : CoroutineDispatcher = Dispatchers.IO

}





