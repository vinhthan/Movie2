package com.thanmanhvinh.movieandnews.ui.main.movie.see_all.top_rated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment


class SeeAllTopRatedFragment : BaseFragment<SeeAllTopRatedViewModel>() {
    override fun createViewModel(): Class<SeeAllTopRatedViewModel> = SeeAllTopRatedViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_see_all_top_rated

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

    }

    override fun initData() {

    }


}