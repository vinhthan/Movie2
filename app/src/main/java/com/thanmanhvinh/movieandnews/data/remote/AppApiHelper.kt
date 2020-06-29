package com.thanmanhvinh.movieandnews.data.remote

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.thanmanhvinh.movieandnews.data.api.*
import io.reactivex.Observable
import javax.inject.Singleton
import javax.inject.Inject

@Singleton
class AppApiHelper @Inject constructor() : ApiHelper {

    override fun doGetMovieNowPlaying(movieNowPlayingRequest: MovieNowPlayingRequest): Observable<MovieNowPlaying> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_NOW_PLAYING)
            .addQueryParameter(movieNowPlayingRequest)
            .build()
            .getObjectObservable(MovieNowPlaying::class.java)
    }

    override fun doGetMovieUpcoming(movieUpcomingRequest: MovieUpcomingRequest): Observable<MovieUpcoming> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_UPCOMING)
            .addQueryParameter(movieUpcomingRequest)
            .build()
            .getObjectObservable(MovieUpcoming::class.java)
    }

    override fun doGetMoviePopular(moviePopularRequest: MoviePopularRequest): Observable<MoviePopular> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_POPULAR)
            .addQueryParameter(moviePopularRequest)
            .build()
            .getObjectObservable(MoviePopular::class.java)
    }

    override fun doGetMovieTopRated(movieTopRatedRequest: MovieTopRatedRequest): Observable<MovieTopRated> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_TOP_RATED)
            .addQueryParameter(movieTopRatedRequest)
            .build()
            .getObjectObservable(MovieTopRated::class.java)
    }

    override fun doGetNowPlayingPage(movieNowPlayingRequestPage: MovieNowPlayingRequestPage): Observable<MovieNowPlaying> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_NOW_PLAYING)
            .addQueryParameter(movieNowPlayingRequestPage)
            .build()
            .getObjectObservable(MovieNowPlaying::class.java)
    }

    override fun doGetPopularPage(moviePopularRequestPage: MoviePopularRequestPage): Observable<MoviePopular> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_POPULAR)
            .addQueryParameter(moviePopularRequestPage)
            .build()
            .getObjectObservable(MoviePopular::class.java)
    }

    override fun doGetTopRatedPage(movieTopRatedRequestPage: MovieTopRatedRequestPage): Observable<MovieTopRated> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_TOP_RATED)
            .addQueryParameter(movieTopRatedRequestPage)
            .build()
            .getObjectObservable(MovieTopRated::class.java)
    }

    override fun doGetUpcomingPage(movieUpcomingRequestPage: MovieUpcomingRequestPage): Observable<MovieUpcoming> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_UPCOMING)
            .addQueryParameter(movieUpcomingRequestPage)
            .build()
            .getObjectObservable(MovieUpcoming::class.java)
    }

    override fun doGetMovieDetail(id: Int, movieDetailRequest: MovieDetailRequest): Observable<MovieDetail> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_DETAIL)
            .addQueryParameter(movieDetailRequest)
            .addPathParameter("movie_id", id.toString())
            .build()
            .getObjectObservable(MovieDetail::class.java)
    }


}