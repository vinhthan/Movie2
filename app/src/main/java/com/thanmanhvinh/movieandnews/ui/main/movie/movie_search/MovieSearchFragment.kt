package com.thanmanhvinh.movieandnews.ui.main.movie.movie_search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment


class MovieSearchFragment : BaseFragment<MovieSearchViewModel>() {
    override fun createViewModel(): Class<MovieSearchViewModel> = MovieSearchViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_movie_search

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

    }

    override fun initData() {

    }


}