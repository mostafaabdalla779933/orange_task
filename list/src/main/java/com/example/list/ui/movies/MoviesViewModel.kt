package com.example.list.ui.movies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseCommand
import com.example.core.base.BaseViewModel
import com.example.core.model.MovieModel
import com.example.list.domain.usecase.MoviesUseCase
import com.example.orange_task.di.scheduler.SchedulersProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow


import javax.inject.Inject



class MoviesViewModel  @Inject constructor(
    val useCase: MoviesUseCase,
    val schedulers: SchedulersProvider
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
        CoroutineScope(Dispatchers.IO+coroutineExceptionHandler).launch {
           state.value = ListViewState.MovieList(useCase.getMoviesCoro().body()?.results ?: listOf())
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, t ->
        t.printStackTrace()
        Log.i("main", ": ${t.message}")
    }

}