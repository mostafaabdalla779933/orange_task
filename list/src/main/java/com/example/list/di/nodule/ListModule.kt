package com.example.list.di.nodule

import com.example.core.network.ApiService
import com.example.list.di.scope.ListScope
import com.example.list.domain.repository.MoviesRepo
import com.example.list.domain.repository.MoviesRepoImp
import com.example.list.domain.usecase.MoviesUseCase
import com.example.list.domain.usecase.MoviesUseCaseImp
import dagger.Module
import dagger.Provides



@Module
class ListModule {

    @ListScope
    @Provides
    fun provideNewsRepo(apiService: ApiService) : MoviesRepo {
       return MoviesRepoImp(apiService)
    }

    @ListScope
    @Provides
    fun provideNewsUseCase(newsRepoImp: MoviesRepoImp) : MoviesUseCase {
        return MoviesUseCaseImp(newsRepoImp)
    }

}