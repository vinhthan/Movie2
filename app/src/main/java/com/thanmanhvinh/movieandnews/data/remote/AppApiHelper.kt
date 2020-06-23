package com.thanmanhvinh.movieandnews.data.remote

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.thanmanhvinh.movieandnews.data.api.*
import io.reactivex.Observable
import javax.inject.Singleton
import javax.inject.Inject

@Singleton
class AppApiHelper @Inject constructor() : ApiHelper {

    override fun doGetMovieNowPlaying(): Observable<MovieNowPlaying> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_NOW_PLAYING)
            .build()
            .getObjectObservable(MovieNowPlaying::class.java)
    }

    override fun doGetMovieUpcoming(): Observable<MovieUpcoming> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_UPCOMING)
            .build()
            .getObjectObservable(MovieUpcoming::class.java)
    }

    override fun doGetMoviePopular(): Observable<MoviePopular> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_POPULAR)
            .build()
            .getObjectObservable(MoviePopular::class.java)
    }

    override fun doGetMovieTopRated(): Observable<MovieTopRated> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_TOP_RATED)
            .build()
            .getObjectObservable(MovieTopRated::class.java)
    }



}