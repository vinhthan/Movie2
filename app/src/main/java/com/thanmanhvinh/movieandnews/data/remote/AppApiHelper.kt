package com.thanmanhvinh.movieandnews.data.remote

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.thanmanhvinh.movieandnews.data.api.*
import io.reactivex.Observable
import javax.inject.Singleton
import javax.inject.Inject

@Singleton
class AppApiHelper @Inject constructor() : ApiHelper {

    /**
     * Load 1 page at home screen
     */
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

    /**
     * Load more
     */
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

    /**
     * Get detail
     */
    override fun doGetMovieDetail(id: Int, movieDetailRequest: MovieDetailRequest): Observable<MovieDetail> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_DETAIL)
            .addPathParameter("movie_id", id.toString())
            .addQueryParameter(movieDetailRequest)
            .build()
            .getObjectObservable(MovieDetail::class.java)
    }

    /**
     * Get video
     */
    override fun doGetMovieVideo(id: Int, movieVideoRequest: MovieVideoRequest): Observable<MovieVideo> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_VIDEO)
            .addPathParameter("movie_id", id.toString())
            .addQueryParameter(movieVideoRequest)
            .build()
            .getObjectObservable(MovieVideo::class.java)
    }

    /**
     * Search movie
     */
    override fun doGetMovieSearch(movieSearchRequest: MovieSearchRequest): Observable<MovieSearch> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_SEARCH)
            .addQueryParameter(movieSearchRequest)
            .build()
            .getObjectObservable(MovieSearch::class.java)
    }

    /**
     * Get review
     */
    override fun doGetReview(id: Int, movieReviewRequest: MovieReviewRequest): Observable<MovieReview> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_REVIEW)
            .addPathParameter("movie_id", id.toString())
            .addQueryParameter(movieReviewRequest)
            .build()
            .getObjectObservable(MovieReview::class.java)
    }

    /**
     * get similar
     */
    override fun doGetSimilar(id: Int, movieSimilarRequest: MovieSimilarRequest): Observable<MovieSimilar> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE_SIMILAR)
            .addPathParameter("movie_id", id.toString())
            .addQueryParameter(movieSimilarRequest)
            .build()
            .getObjectObservable(MovieSimilar::class.java)
    }

    /**
     * get token
     */
    override fun doGetToken(tokenRequest: TokenRequest): Observable<Token> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_TOKEN)
            .addQueryParameter(tokenRequest)
            .build()
            .getObjectObservable(Token::class.java)
    }

    /**
     * login
     */
    override fun doLogin(loginRequest: LoginRequest): Observable<Login> {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_MOVIE_LOGIN)
            .addQueryParameter(loginRequest)
            .build()
            .getObjectObservable(Login::class.java)
    }

    /**
     * logout
     */
    override fun doLogout(logoutRequest: LogoutRequest): Observable<Logout> {
        return Rx2AndroidNetworking.delete(ApiEndPoint.ENDPOINT_MOVIE_LOGOUT)
            .addQueryParameter(logoutRequest)
            .build()
            .getObjectObservable(Logout::class.java)
    }




}