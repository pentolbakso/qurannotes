package com.kodebonek.qurannotes.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.kodebonek.qurannotes.ui.MainViewModel
import com.kodebonek.qurannotes.viewmodel.MyViewModelFactory

/**
 * @author <@Po10cio> on 10/4/17 for KotlinDagger
 *

 */
@Module
abstract class ViewModelModule {

    /**
     * Binds the MainViewModel into the dagger component hierarchy
     * */
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel: MainViewModel): ViewModel


    /**
     * Provides the MyViewModelFactory
     * */
    @Binds
    abstract fun provideViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory


}