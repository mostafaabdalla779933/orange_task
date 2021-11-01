package com.example.list.ui.movies

import com.example.core.model.MovieModel

sealed class ListViewState {

    class MovieList(val list: List<MovieModel>):ListViewState()
    object Idel:ListViewState()
}