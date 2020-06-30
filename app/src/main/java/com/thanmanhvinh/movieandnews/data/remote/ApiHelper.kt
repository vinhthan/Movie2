package com.thanmanhvinh.movieandnews.data.remote

import com.thanmanhvinh.movieandnews.data.api.*
import io.reactivex.Observable


interface ApiHelper {

    /**
     * Load 1 page at home screen
     */
    fun doGetMovieNowPlaying(movieNowPlayingRequest: MovieNowPlayingRequest): Observable<MovieNowPlaying>
    fun doGetMovieUpcoming(movieUpcomingRequest: MovieUpcomingRequest): Observable<MovieUpcoming>
    fun doGetMoviePopular(moviePopularRequest: MoviePopularRequest): Observable<MoviePopular>
    fun doGetMovieTopRated(movieTopRatedRequest: MovieTopRatedRequest):Observable<MovieTopRated>

    /**
     * Load more
     */
    fun doGetNowPlayingPage(movieNowPlayingRequestPage: MovieNowPlayingRequestPage): Observable<MovieNowPlaying>
    fun doGetPopularPage(moviePopularRequestPage: MoviePopularRequestPage): Observable<MoviePopular>
    fun doGetTopRatedPage(movieTopRatedRequestPage: MovieTopRatedRequestPage): Observable<MovieTopRated>
    fun doGetUpcomingPage(movieUpcomingRequestPage: MovieUpcomingRequestPage): Observable<MovieUpcoming>

    /**
     * Detail
     */
    fun doGetMovieDetail(id: Int, movieDetailRequest: MovieDetailRequest): Observable<MovieDetail>

}