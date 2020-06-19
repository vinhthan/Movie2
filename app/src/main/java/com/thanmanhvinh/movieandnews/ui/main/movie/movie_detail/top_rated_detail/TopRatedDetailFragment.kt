package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.top_rated_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment

class TopRatedDetailFragment : BaseFragment<TopRatedDetailViewModel>() {
    override fun createViewModel(): Class<TopRatedDetailViewModel> = TopRatedDetailViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_top_rated_detailragment

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

    }

    override fun initData() {

    }


}