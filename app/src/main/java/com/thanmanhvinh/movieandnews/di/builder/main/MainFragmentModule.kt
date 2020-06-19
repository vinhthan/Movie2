package com.thanmanhvinh.movieandnews.di.builder.main

import com.thanmanhvinh.movieandnews.ui.main.movie.MovieFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.now_playing_detail.NowPlayingDetailFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.popular_detail.PopularDetailFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.top_rated_detail.TopRatedDetailFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.upcoming_detail.UpcomingDetailFragment
import com.thanmanhvinh.movieandnews.ui.main.news.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector
    abstract fun movieFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun newsFragment(): NewsFragment

    @ContributesAndroidInjector
    abstract fun nowPlayingDetailFragment(): NowPlayingDetailFragment

    @ContributesAndroidInjector
    abstract fun upcomingDetailFragment() :UpcomingDetailFragment

    @ContributesAndroidInjector
    abstract fun topRatedDetaiFlragment(): TopRatedDetailFragment

    @ContributesAndroidInjector
    abstract fun popularDetailFragment(): PopularDetailFragment

}