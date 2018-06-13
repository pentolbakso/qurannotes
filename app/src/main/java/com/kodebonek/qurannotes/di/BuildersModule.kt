package com.kodebonek.qurannotes.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.kodebonek.qurannotes.ui.MainActivity

/**
 * Created by <@Po10cio> on 8/8/17 for KotlinDagger
 *
 *

 */

@Module
abstract class BuildersModule {
    /**
     * Provides the injector for the [com.kodebonek.qurannotes.ui.MainActivity],
     * which has access to the dependencies provided by the application instance (Singleton scope)
     */
    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun conributeChooserActivity(): MainActivity


}
