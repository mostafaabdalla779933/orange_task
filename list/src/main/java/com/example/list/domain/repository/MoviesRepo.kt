package com.example.list.domain.repository

import com.example.core.model.MovieResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response

interface MoviesRepo {
    fun getMovies(): Observable<MovieResponse>

    suspend fun getMoviesCoro(): Response<MovieResponse>
}