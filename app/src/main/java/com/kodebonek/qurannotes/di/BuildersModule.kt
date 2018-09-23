package com.kodebonek.qurannotes.di

import com.kodebonek.qurannotes.ui.download.DownloadActivity
import com.kodebonek.qurannotes.ui.download.SelectEditionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.kodebonek.qurannotes.ui.main.MainActivity
import com.kodebonek.qurannotes.ui.splash.SplashActivity

@Module
abstract class BuildersModule {
    /**
     * Provides the injector for the [com.kodebonek.qurannotes.ui.MainActivity],
     * which has access to the dependencies provided by the application instance (Singleton scope)
     */
    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun contributeSplashActivity(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun contributeDownloadActivity(): DownloadActivity

    //Fragments

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun contributeSelectEditionFragment(): SelectEditionFragment

}
