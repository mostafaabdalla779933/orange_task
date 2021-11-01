package com.example.core.di.module

import android.app.Application
import android.content.Context

import com.example.core.di.scheduler.SchedulersFacade
import com.example.orange_task.di.scheduler.SchedulersProvider
import dagger.Binds
import dagger.Module



@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    @Binds
    abstract fun providerScheduler(schedulersFacade: SchedulersFacade): SchedulersProvider
}





