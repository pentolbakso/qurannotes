package com.kodebonek.qurannotes

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import com.kodebonek.qurannotes.di.AppComponent
import com.kodebonek.qurannotes.di.AppInjector
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber
import javax.inject.Inject

/**
 * @author <@Po10cio> on 10/3/17 for KotlinDagger
 *

 */
class App : Application(), HasActivityInjector {

    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
        @Inject set

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        AppInjector.initInjector(this)

        ViewPump.init(ViewPump.builder()
                .addInterceptor(CalligraphyInterceptor(CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Shanti_Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build())).build()
        )

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }


    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityInjector
    }
}