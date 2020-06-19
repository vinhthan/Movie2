package com.thanmanhvinh.movieandnews.di.component

import android.app.Application
import com.thanmanhvinh.movieandnews.MovieAndNewsApplication
import com.thanmanhvinh.movieandnews.di.builder.ActivityBuilder
import com.thanmanhvinh.movieandnews.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule:: class, AppModule::class, ActivityBuilder::class])
interface AppComponent : AndroidInjector<MovieAndNewsApplication> {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}