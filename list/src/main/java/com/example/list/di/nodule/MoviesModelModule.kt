package com.example.list.di.nodule

import androidx.lifecycle.ViewModel
import com.example.list.di.scope.ListScope
import com.example.list.ui.movies.MoviesViewModel
import com.example.orange_task.di.viewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap



@Module
abstract class MoviesModelModule {


      @Binds
      @IntoMap
      @ListScope
      @ViewModelKey(MoviesViewModel::class)
      internal abstract fun bindNewsViewModel(viewModel: MoviesViewModel): ViewModel
}
