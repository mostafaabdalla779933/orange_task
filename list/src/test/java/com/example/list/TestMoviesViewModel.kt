package com.example.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.list.ui.movies.MoviesViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test



//@RunWith(AndroidJUnit4::class)
class TestMoviesViewModel{

    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    private lateinit var viewModel : MoviesViewModel

    @Before
    fun setup(){

        //viewModel = MoviesViewModel(MockMoviesUsecase(), Schedulers.)
    }

    @Test
    fun testgetMovies(){


    }


}