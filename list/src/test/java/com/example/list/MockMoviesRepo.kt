package com.example.list

import com.example.core.model.MovieModel
import com.example.core.model.MovieResponse
import com.example.list.domain.repository.MoviesRepo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.internal.operators.observable.ObservableJust


class MockMoviesRepo: MoviesRepo {
    override fun getMovies(): Observable<MovieResponse> {
         return ObservableJust<MovieResponse>(
             MovieResponse(
                 results = listOf(
                     MovieModel(overview = "overview",title = "title")
                 )
             )
         )
    }
}