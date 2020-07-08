package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.review_similar.similar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment


class SimilarFragment : BaseFragment<SimilarViewModel>() {
    override fun createViewModel(): Class<SimilarViewModel> = SimilarViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_similar

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

    }

    override fun initData() {

    }


}