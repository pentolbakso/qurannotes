package com.kodebonek.qurannotes.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.kodebonek.qurannotes.ui.main.MainViewModel
import com.kodebonek.qurannotes.ui.splash.SplashViewModel
import com.kodebonek.qurannotes.viewmodel.MyViewModelFactory
import com.kodebonek.qurannotes.ui.download.SelectEditionViewModel
import com.kodebonek.qurannotes.ui.surah.SurahListViewModel
import com.kodebonek.qurannotes.ui.surah.SurahViewModel

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
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SelectEditionViewModel::class)
    abstract fun bindSelectEditionViewModel(viewModel: SelectEditionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SurahListViewModel::class)
    abstract fun bindSurahListViewModel(viewModel: SurahListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SurahViewModel::class)
    abstract fun bindSurahViewModel(viewModel: SurahViewModel): ViewModel

    /**
     * Provides the MyViewModelFactory
     * */
    @Binds
    abstract fun provideViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory


}