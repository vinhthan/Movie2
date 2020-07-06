package com.thanmanhvinh.movieandnews.di.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.thanmanhvinh.movieandnews.data.AppDataManager
import com.thanmanhvinh.movieandnews.data.DataManager
import com.thanmanhvinh.movieandnews.data.local.AppPreferencesHelper
import com.thanmanhvinh.movieandnews.data.local.PreferencesHelper
import com.thanmanhvinh.movieandnews.di.Preferences
import com.thanmanhvinh.movieandnews.utils.rx.AppSchedulerProvider
import com.thanmanhvinh.movieandnews.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context{
        return application
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    fun provideAppSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Preferences
    fun providePreferenceName(): String {
        return "pref_app_name"
    }

    @Provides
    @Singleton
    fun provideGson(): Gson? {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun providerPreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelper
    }






}