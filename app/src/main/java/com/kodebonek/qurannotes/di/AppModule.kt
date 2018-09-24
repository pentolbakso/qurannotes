package com.kodebonek.qurannotes.di


import android.arch.persistence.room.Room
import android.content.Context
import com.djakartalloyd.dlmarket.util.PreferencesHelper
import dagger.Module
import dagger.Provides
import com.kodebonek.qurannotes.App
import com.kodebonek.qurannotes.data.db.AppDatabase
import com.kodebonek.qurannotes.data.remote.ApiService
import com.kodebonek.qurannotes.data.repository.QuranRepository
import com.kodebonek.qurannotes.data.repository.QuranRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    internal val PREF_NAME: String = "qurannotes_preferences"

    @Singleton
    @Provides
    internal fun provideContext(): Context {
        return app.applicationContext
    }

    @Singleton
    @Provides
    internal fun providePreferences(context: Context): PreferencesHelper {
        return PreferencesHelper(context, PREF_NAME)
    }

    @Singleton
    @Provides
    internal fun provideQuranRepository(apiService: ApiService, appDatabase: AppDatabase): QuranRepository {
        return QuranRepositoryImpl(apiService, appDatabase)
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {
        val logInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            message -> Timber.tag("OkHttp").d(message)
        })
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(logInterceptor)
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(300, TimeUnit.SECONDS)

        return Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    internal fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    internal fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "QuranNotes.db")
                .fallbackToDestructiveMigration()   //clear DB on version upgrade
                .build()
    }

}
