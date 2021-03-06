package com.thanmanhvinh.movieandnews.data.remote

object ApiEndPoint {
    const val ENDPOINT_MOVIE_UPCOMING = "https://api.themoviedb.org/3/movie/upcoming"
    const val ENDPOINT_MOVIE_TOP_RATED = "https://api.themoviedb.org/3/movie/top_rated"
    const val ENDPOINT_MOVIE_NOW_PLAYING = "https://api.themoviedb.org/3/movie/now_playing"
    const val ENDPOINT_MOVIE_POPULAR = "https://api.themoviedb.org/3/movie/popular"

    const val ENDPOINT_MOVIE_DETAIL = "https://api.themoviedb.org/3/movie/{movie_id}"
    const val ENDPOINT_MOVIE_VIDEO = "https://api.themoviedb.org/3/movie/{movie_id}/videos"
    const val ENDPOINT_MOVIE_SEARCH = "https://api.themoviedb.org/3/search/movie"
    const val ENDPOINT_MOVIE_REVIEW = "https://api.themoviedb.org/3/movie/{movie_id}/reviews"

    const val ENDPOINT_TOKEN = "https://api.themoviedb.org/3/authentication/token/new"
    const val ENDPOINT_MOVIE_LOGIN = "https://api.themoviedb.org/3/authentication/token/validate_with_login"
    const val ENDPOINT_MOVIE_SIMILAR = "https://api.themoviedb.org/3/movie/{movie_id}/similar"
    const val ENDPOINT_MOVIE_LOGOUT = "https://api.themoviedb.org/3/authentication/session"



}

//{movie_id}: 157336
//api key: 034bbd1b233d6726e0c7dc7f338657f9
//url: https://api.themoviedb.org/3/movie/popular?api_key=034bbd1b233d6726e0c7dc7f338657f9&page=1
//poster: https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg

//key video: 5lGoQhFb4NM
//video: https://www.youtube.com/watch?v=5lGoQhFb4NM

//image from youtube: https://i3.ytimg.com/vi/5lGoQhFb4NM/hqdefault.jpg  //5lGoQhFb4NM is key video

//login
//api_key 034bbd1b233d6726e0c7dc7f338657f9
//username thanmanhvinh
//password vinhmcmvc
//request_token c4d1514744c59f936df788570de331dd786a4a69 //co han sd

//logout
//api_key 034bbd1b233d6726e0c7dc7f338657f9
//"session_id": "2629f70fb498edc263a0adb99118ac41f0053e8c"
//