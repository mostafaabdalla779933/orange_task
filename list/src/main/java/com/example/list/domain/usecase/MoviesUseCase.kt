package com.example.list.domain.usecase

import com.example.core.model.MovieResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response

interface MoviesUseCase {
    fun getMovies(): Observable<MovieResponse>

    suspend fun getMoviesCoro(): Response<MovieResponse>
}