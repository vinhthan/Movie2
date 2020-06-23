package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.upcoming_detail

import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieUpcoming
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_upcoming_detail.*
import kotlinx.android.synthetic.main.include_detail.*


class UpcomingDetailFragment : BaseFragment<UpcomingDetailViewModel>() {

    override fun createViewModel(): Class<UpcomingDetailViewModel> =
        UpcomingDetailViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_upcoming_detail

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

    }

    override fun initData() {
        val bundle = arguments?.getSerializable("MOVIE_UPCOMING_DETAIL")
        val movieUpcoming = bundle?.let {
            bundle as MovieUpcoming.Result
        }

        movieUpcoming?.let {
            val imgBig = it.getImageBackdropPathUpcoming()
            val imgSmall = it.getImagePosterPathUpcoming()
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