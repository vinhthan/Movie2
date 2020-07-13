package com.thanmanhvinh.movieandnews.di.builder

import com.thanmanhvinh.movieandnews.di.builder.main.MainFragmentModule
import com.thanmanhvinh.movieandnews.di.builder.main_2.MainFragmentModule2
import com.thanmanhvinh.movieandnews.ui.main.MainActivity
import com.thanmanhvinh.movieandnews.ui.main_2.MainActivity2
import com.thanmanhvinh.movieandnews.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [MainFragmentModule2::class])
    abstract fun mainActivity2(): MainActivity2



}