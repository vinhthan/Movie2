package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.now_playing_detail

import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieNowPlaying
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_now_playing_detail.*


class NowPlayingDetailFragment : BaseFragment<NowPlayingDetailViewModel>(){
    override fun createViewModel(): Class<NowPlayingDetailViewModel> = NowPlayingDetailViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_now_playing_detail

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {
        val output = mViewModel.transform(
            Any()
        )

        with(output){
            detail.observeOn(schedulerProvider.ui)
                .subscribe { detail ->
                    showDetail(detail)

                }.addToDisposable()
        }
    }

    override fun initData() {

    }

    private fun showDetail(detailMovie: MovieNowPlaying.Results){
        Glide.with(this).load(detailMovie.getImageBackdropPathNowPlaying()).into(imgBig)
        Glide.with(this).load(detailMovie.getImagePosterPathNowPlaying()).into(imgSmall)


    }


}