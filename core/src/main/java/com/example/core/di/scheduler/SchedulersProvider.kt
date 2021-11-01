package com.example.orange_task.di.scheduler

import io.reactivex.rxjava3.core.Scheduler

interface SchedulersProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
    fun computation(): Scheduler
}