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

    override fun doGetMovieNowPlaying(movieNowPlayingRequest: MovieNowPlayingRequest): Observable<MovieNowPlaying> {
        return mApiService.doGetMovieNowPlaying(movieNowPlayingRequest)
    }

    override fun doGetMovieUpcoming(): Observable<MovieUpcoming> {
        return mApiService.doGetMovieUpcoming()
    }

    override fun doGetMoviePopular(): Observable<MoviePopular> {
        return mApiService.doGetMoviePopular()
    }

    override fun doGetMovieTopRated(): Observable<MovieTopRated> {
        return mApiService.doGetMovieTopRated()
    }



}