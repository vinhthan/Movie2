package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.upcoming_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment


class UpcomingDetailFragment : BaseFragment<UpcomingDetailViewModel>() {
    override fun createViewModel(): Class<UpcomingDetailViewModel> = UpcomingDetailViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_upcoming_detail

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

    }

    override fun initData() {

    }

}