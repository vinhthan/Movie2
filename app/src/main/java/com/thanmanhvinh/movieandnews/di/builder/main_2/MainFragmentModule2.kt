package com.thanmanhvinh.movieandnews.di.builder.main_2

import com.thanmanhvinh.movieandnews.ui.main_2.intro.IntroFragment
import com.thanmanhvinh.movieandnews.ui.main_2.no_internet.NoInternetFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module

abstract class MainFragmentModule2 {
    @ContributesAndroidInjector
    abstract fun noInternetFragment(): NoInternetFragment

    @ContributesAndroidInjector
    abstract fun intro2Fragment(): IntroFragment
}