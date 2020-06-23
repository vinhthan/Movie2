package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.now_playing_detail

import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieNowPlaying
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import kotlinx.android.synthetic.main.include_detail.*


class NowPlayingDetailFragment : BaseFragment<NowPlayingDetailViewModel>() {
    override fun createViewModel(): Class<NowPlayingDetailViewModel> =
        NowPlayingDetailViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_now_playing_detail

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

    }

    override fun initData() {
        val bundle  = arguments?.getSerializable("MOVIE_NOW_PLAYING_DETAIL")
        val movieNowPlaying = bundle?.let {
            bundle as MovieNowPlaying.Results
        }

        movieNowPlaying?.let {
            val imageBig = it.getImageBackdropPathNowPlaying()
            val imgSmall = it.getImagePosterPathNowPlaying()
            val title = it.title
            val date = it.releaseDate
            val vote = it.voteAverage
            val overview= it.overview
            val popularity = it.popularity
            Glide.with(this).load(imageBig).into(imgBigDetail)
            Glide.with(this).load(imgSmall).into(imgSmallDetail)
            tvTitleDetail.text = title
            tvDateDetail.text = date
            tvVoteDetail.text = vote.toString()
            tvOverviewDetail.text = overview
            tvPopularityDetail.text = popularity.toString()

        }

    }

}