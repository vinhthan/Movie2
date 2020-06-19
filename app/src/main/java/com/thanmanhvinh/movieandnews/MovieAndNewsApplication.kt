package com.thanmanhvinh.movieandnews

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.gsonparserfactory.GsonParserFactory
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.google.gson.GsonBuilder
import com.thanmanhvinh.movieandnews.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.CompletableSubject
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class MovieAndNewsApplication : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        AndroidNetworking.setParserFactory(GsonParserFactory(gson))
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        AndroidNetworking.initialize(applicationContext, okHttpClient)
        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BASIC)


    }




    val onInitialized = CompletableSubject.create()

    private val compositeDisposable: CompositeDisposable
        get() = CompositeDisposable()

    companion object {
        private lateinit var movieAndNewsApplication: MovieAndNewsApplication

        fun get(): MovieAndNewsApplication {
            return movieAndNewsApplication
        }
    }


}