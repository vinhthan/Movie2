package com.thanmanhvinh.movieandnews.data.remote

import com.thanmanhvinh.movieandnews.data.api.*
import io.reactivex.Observable


interface ApiHelper {

    fun doGetMovieNowPlaying(movieNowPlayingRequest: MovieNowPlayingRequest): Observable<MovieNowPlaying>

    fun doGetMovieUpcoming(movieUpcomingRequest: MovieUpcomingRequest): Observable<MovieUpcoming>

    fun doGetMoviePopular(moviePopularRequest: MoviePopularRequest): Observable<MoviePopular>

    fun doGetMovieTopRated(movieTopRatedRequest: MovieTopRatedRequest):Observable<MovieTopRated>

}