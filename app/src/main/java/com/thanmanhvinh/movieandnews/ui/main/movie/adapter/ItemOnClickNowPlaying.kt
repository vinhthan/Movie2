package com.thanmanhvinh.movieandnews.ui.main.movie.adapter

import com.thanmanhvinh.movieandnews.data.api.MovieNowPlaying

interface ItemOnClickNowPlaying {
    fun OnItemClickNowPlaying(position: MovieNowPlaying.Results)
}