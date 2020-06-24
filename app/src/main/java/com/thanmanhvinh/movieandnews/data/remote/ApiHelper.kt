package com.thanmanhvinh.movieandnews.data.remote

import com.thanmanhvinh.movieandnews.data.api.*
import io.reactivex.Observable


interface ApiHelper {

    fun doGetMovieNowPlaying(movieNowPlayingRequest: MovieNowPlayingRequest): Observable<MovieNowPlaying>

    fun doGetMovieUpcoming(): Observable<MovieUpcoming>

    fun doGetMoviePopular(): Observable<MoviePopular>

    fun doGetMovieTopRated():Observable<MovieTopRated>

}