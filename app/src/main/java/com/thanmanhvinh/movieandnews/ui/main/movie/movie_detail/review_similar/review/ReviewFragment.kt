package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.review_similar.review

import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment


class ReviewFragment : BaseFragment<ReviewViewModel>() {
    override fun createViewModel(): Class<ReviewViewModel> = ReviewViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_review

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

    }

    override fun initData() {

    }


}