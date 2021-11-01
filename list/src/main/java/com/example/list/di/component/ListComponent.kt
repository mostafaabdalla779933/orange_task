package com.example.list.di.component


import com.example.list.di.nodule.ListModule
import com.example.list.di.scope.ListScope
import com.example.list.di.nodule.MoviesModelModule
import com.example.list.domain.repository.MoviesRepo
import com.example.list.domain.usecase.MoviesUseCase
import com.example.list.ui.movies.MoviesViewModel
import com.example.list.ui.host.HostActivity
import com.example.list.ui.movies.ListFragment
import com.example.orange_task.di.component.AppComponent
import dagger.Component



@ListScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ListModule::class,
        MoviesModelModule::class]
)
interface ListComponent {

    @Component.Builder
    interface Builder{

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): ListComponent

    }

    fun inject(listFragment: ListFragment)

    fun inject(hostActivity: HostActivity)

    fun getNewsRepo() : MoviesRepo

    fun getNewsUseCase() : MoviesUseCase

    fun getNewsViewModel() : MoviesViewModel

}