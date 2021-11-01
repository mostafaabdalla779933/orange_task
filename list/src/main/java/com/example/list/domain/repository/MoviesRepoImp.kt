package com.example.list.domain.repository


import com.example.core.model.MovieResponse
import com.example.core.network.ApiService
import com.example.core.network.Constant
import com.example.list.domain.repository.MoviesRepo
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MoviesRepoImp @Inject constructor(val apiService: ApiService) : MoviesRepo {

    override fun getMovies(): Observable<MovieResponse> = apiService.getRequest (Constant.api_key)
    override suspend fun getMoviesCoro() = apiService.getRequestCoro (Constant.api_key)
}