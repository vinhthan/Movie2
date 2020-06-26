package com.thanmanhvinh.movieandnews.ui.main.movie.see_all.upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment


class SeeAllUpcomingFragment : BaseFragment<SeeAllUpcomingViewModel>() {
    override fun createViewModel(): Class<SeeAllUpcomingViewModel> = SeeAllUpcomingViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_see_all_upcoming

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

    }

    override fun initData() {

    }


}