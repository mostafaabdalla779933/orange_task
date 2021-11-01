package com.example.list.domain.usecase


import com.example.list.domain.repository.MoviesRepo
import javax.inject.Inject

class MoviesUseCaseImp @Inject constructor(val repo: MoviesRepo) : MoviesUseCase {

    override fun getMovies() =  repo.getMovies()
    override suspend fun getMoviesCoro() = repo.getMoviesCoro()
}