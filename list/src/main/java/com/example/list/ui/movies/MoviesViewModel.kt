package com.example.list.ui.movies

import android.util.Log
import com.example.core.base.BaseViewModel
import com.example.core.di.module.DISPATCHER_IO
import com.example.core.di.module.DISPATCHER_MAIN_THREAD
import com.example.list.domain.usecase.MoviesUseCase
import com.example.orange_task.di.scheduler.SchedulersProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow


import javax.inject.Inject
import javax.inject.Named


class MoviesViewModel  @Inject constructor(
    val useCase: MoviesUseCase,
    @Named(DISPATCHER_MAIN_THREAD)
    val dispatcherMain : CoroutineDispatcher,
    @Named(DISPATCHER_IO)
    val dispatcherIo:CoroutineDispatcher
): BaseViewModel() {


    val channel = Channel<ListIntent>(Channel.UNLIMITED)
    val state = MutableStateFlow<ListViewState>(ListViewState.Idel)


    init {
        processIntent()
    }

    private fun processIntent(){
        CoroutineScope(Dispatchers.IO).launch {
            channel.consumeAsFlow().collect {
                when(it){
                 is ListIntent.GetList ->{ getList() }
                }
            }
        }
    }
    private fun getList(){
        CoroutineScope(dispatcherIo+coroutineExceptionHandler).launch {
           state.value = ListViewState.MovieList(useCase.getMoviesCoro().body()?.results ?: listOf())
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, t ->
        t.printStackTrace()
        Log.i("main", ": ${t.message}")
    }

}