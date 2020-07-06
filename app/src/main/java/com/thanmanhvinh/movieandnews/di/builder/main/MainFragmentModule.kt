package com.thanmanhvinh.movieandnews.di.builder.main

import com.thanmanhvinh.movieandnews.ui.main.movie.MovieFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.login.LoginFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.now_playing_detail.NowPlayingDetailFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.popular_detail.PopularDetailFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.top_rated_detail.TopRatedDetailFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.upcoming_detail.UpcomingDetailFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.video.ListVideoFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.video.play_video.PlayVideoFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_search.MovieSearchFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.see_all.now_playing.SeeAllNowPlayingFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.see_all.popular.SeeAllPopularFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.see_all.top_rated.SeeAllTopRatedFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.see_all.upcoming.SeeAllUpcomingFragment
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

    @ContributesAndroidInjector
    abstract fun movieSearchFragment(): MovieSearchFragment

    @ContributesAndroidInjector
    abstract fun seeAllNowPlayingFragment(): SeeAllNowPlayingFragment

    @ContributesAndroidInjector
    abstract fun seeAllPopularFragment(): SeeAllPopularFragment

    @ContributesAndroidInjector
    abstract fun seeAllTopRatedFragment(): SeeAllTopRatedFragment

    @ContributesAndroidInjector
    abstract fun seeAllUpcomingFragment(): SeeAllUpcomingFragment

    @ContributesAndroidInjector
    abstract fun videoFragment(): ListVideoFragment

    @ContributesAndroidInjector
    abstract fun playVideoFragment(): PlayVideoFragment

    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment

}