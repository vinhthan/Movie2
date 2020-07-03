package com.thanmanhvinh.movieandnews.data

import com.thanmanhvinh.movieandnews.data.api.*
import com.thanmanhvinh.movieandnews.data.remote.AppApiHelper
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val mApiService: AppApiHelper
): DataManager {

    /**
     * Load 1 page at home screen
     */
    override fun doGetMovieNowPlaying(movieNowPlayingRequest: MovieNowPlayingRequest): Observable<MovieNowPlaying> {
        return mApiService.doGetMovieNowPlaying(movieNowPlayingRequest)
    }
    override fun doGetMovieUpcoming(movieUpcomingRequest: MovieUpcomingRequest): Observable<MovieUpcoming> {
        return mApiService.doGetMovieUpcoming(movieUpcomingRequest)
    }
    override fun doGetMoviePopular(moviePopularRequest: MoviePopularRequest): Observable<MoviePopular> {
        return mApiService.doGetMoviePopular(moviePopularRequest)
    }
    override fun doGetMovieTopRated(movieTopRatedRequest: MovieTopRatedRequest): Observable<MovieTopRated> {
        return mApiService.doGetMovieTopRated(movieTopRatedRequest)
    }

    /**
     * Load more
     */
    override fun doGetNowPlayingPage(movieNowPlayingRequestPage: MovieNowPlayingRequestPage): Observable<MovieNowPlaying> {
        return mApiService.doGetNowPlayingPage(movieNowPlayingRequestPage)
    }
    override fun doGetPopularPage(moviePopularRequestPage: MoviePopularRequestPage): Observable<MoviePopular> {
        return mApiService.doGetPopularPage(moviePopularRequestPage)
    }
    override fun doGetTopRatedPage(movieTopRatedRequestPage: MovieTopRatedRequestPage): Observable<MovieTopRated> {
        return mApiService.doGetTopRatedPage(movieTopRatedRequestPage)
    }
    override fun doGetUpcomingPage(movieUpcomingRequestPage: MovieUpcomingRequestPage): Observable<MovieUpcoming> {
        return mApiService.doGetUpcomingPage(movieUpcomingRequestPage)
    }

    /**
     * Get detail
     */
    override fun doGetMovieDetail(id: Int, movieDetailRequest: MovieDetailRequest): Observable<MovieDetail> {
        return mApiService.doGetMovieDetail(id, movieDetailRequest)
    }

    /**
     * Get video
     */
    override fun doGetMovieVideo(id: Int, movieVideoRequest: MovieVideoRequest): Observable<MovieVideo> {
        return mApiService.doGetMovieVideo(id, movieVideoRequest)
    }

    /**
     * Key words
     */
    override fun doGetMovieSearch(movieSearchRequest: MovieSearchRequest): Observable<MovieSearch> {
        return mApiService.doGetMovieSearch(movieSearchRequest)
    }

    /**
     * Get review
     */
    override fun doGetReview(id: Int, movieReviewRequest: MovieReviewRequest): Observable<MovieReview> {
        return mApiService.doGetReview(id, movieReviewRequest)
    }


}