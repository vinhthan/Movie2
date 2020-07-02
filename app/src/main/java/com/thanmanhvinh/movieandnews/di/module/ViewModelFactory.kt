package com.thanmanhvinh.movieandnews.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thanmanhvinh.movieandnews.data.DataManager
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.ui.main.MainViewModel
import com.thanmanhvinh.movieandnews.ui.main.movie.MovieViewModel
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.now_playing_detail.NowPlayingDetailViewModel
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.popular_detail.PopularDetailViewModel
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.top_rated_detail.TopRatedDetailViewModel
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.upcoming_detail.UpcomingDetailViewModel
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.video.ListVideoViewModel
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.video.play_video.PlayVideoViewModel
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_search.MovieSearchViewModel
import com.thanmanhvinh.movieandnews.ui.main.movie.see_all.now_playing.SeeAllNowPlayingViewModel
import com.thanmanhvinh.movieandnews.ui.main.movie.see_all.popular.SeeAllPopularViewModel
import com.thanmanhvinh.movieandnews.ui.main.movie.see_all.top_rated.SeeAllTopRatedViewModel
import com.thanmanhvinh.movieandnews.ui.main.movie.see_all.upcoming.SeeAllUpcomingViewModel
import com.thanmanhvinh.movieandnews.ui.main.news.NewsViewModel
import com.thanmanhvinh.movieandnews.ui.splash.SplashViewModel
import com.thanmanhvinh.movieandnews.utils.rx.SchedulerProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    val dataManager: DataManager
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass){
            MainViewModel::class.java -> MainViewModel() as T
            MovieViewModel::class.java -> MovieViewModel() as T
            NewsViewModel::class.java -> NewsViewModel() as T
            SplashViewModel::class.java -> SplashViewModel() as T
            NowPlayingDetailViewModel::class.java -> NowPlayingDetailViewModel() as T
            UpcomingDetailViewModel::class.java -> UpcomingDetailViewModel() as T
            TopRatedDetailViewModel::class.java -> TopRatedDetailViewModel() as T
            PopularDetailViewModel::class.java -> PopularDetailViewModel() as T
            MovieSearchViewModel::class.java -> MovieSearchViewModel() as T
            SeeAllNowPlayingViewModel::class.java -> SeeAllNowPlayingViewModel() as T
            SeeAllPopularViewModel::class.java -> SeeAllPopularViewModel() as T
            SeeAllTopRatedViewModel::class.java -> SeeAllTopRatedViewModel() as T
            SeeAllUpcomingViewModel::class.java -> SeeAllUpcomingViewModel() as T
            ListVideoViewModel::class.java -> ListVideoViewModel() as T
            PlayVideoViewModel::class.java -> PlayVideoViewModel() as T


            else -> throw IllegalArgumentException("Unknown ViewModel class ${modelClass.simpleName}")


        }
        if (viewModel is BaseViewModel<*, *>){
            viewModel.initData(dataManager, schedulerProvider)
        }

        return viewModel
    }
}