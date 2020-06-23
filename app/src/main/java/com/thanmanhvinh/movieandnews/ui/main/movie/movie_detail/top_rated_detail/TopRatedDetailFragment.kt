package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.top_rated_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieTopRated
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_popular_detail.*
import kotlinx.android.synthetic.main.fragment_top_rated_detailragment.*
import kotlinx.android.synthetic.main.include_detail.*

class TopRatedDetailFragment : BaseFragment<TopRatedDetailViewModel>() {
    override fun createViewModel(): Class<TopRatedDetailViewModel> = TopRatedDetailViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_top_rated_detailragment

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

    }

    override fun initData() {
        val bundle = arguments?.getSerializable("MOVIE_TOP_RATED_DETAIL")
        val movieTopRated = bundle?.let {
            bundle as MovieTopRated.Results
        }

        movieTopRated?.let {
            val imgBig = it.getImageBackdropPathTopRated()
            val imgSmall = it.getImagePosterPathTopRated()
            val date = it.releaseDate
            val vote = it.voteAverage
            val title = it.title
            val popularity = it.popularity
            val overview = it.overview

            Glide.with(this).load(imgBig).into(imgBigDetail)
            Glide.with(this).load(imgSmall).into(imgSmallDetail)
            tvTitleDetail.text = title
            tvDateDetail.text = date
            tvVoteDetail.text = vote.toString()
            tvOverviewDetail.text = overview
            tvPopularityDetail.text = popularity.toString()
        }
    }

}