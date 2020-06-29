package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.now_playing_detail

import android.widget.Toast
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieDetail
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.include_detail.*


class NowPlayingDetailFragment : BaseFragment<NowPlayingDetailViewModel>() {

    private var id = BehaviorSubject.create<MovieDetail>()

    override fun createViewModel(): Class<NowPlayingDetailViewModel> =
        NowPlayingDetailViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_now_playing_detail

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {
        val id = (arguments?.get("abc") as? MovieDetail)
        if (id == null){

            Toast.makeText(context, "id $id", Toast.LENGTH_SHORT).show()
            return
        }

        val output = mViewModel.transform(
            NowPlayingDetailViewModel.Input(
                id
            )
        )
        with(output) {
            detail.observeOn(schedulerProvider.ui)
                .subscribe { data ->
                    showDetail(data)
                }.addToDisposable()
        }


        imgBackDetail.setOnClickListener {
            onButtonBackClick()
        }
    }

    override var showToolBar: Boolean = false

    override fun initData() {
/*        val bundle  = arguments?.getSerializable(AppConstants.MOVIE_NOW_PLAYING_DETAIL)
        val movieNowPlaying = bundle?.let {
            bundle as MovieNowPlaying.Results
        }

        movieNowPlaying?.let {
            var languages = ""
            val imageBig = it.getImageBackdropPathNowPlaying()
            val imgSmall = it.getImagePosterPathNowPlaying()
            val title = it.title
            val date = it.releaseDate
            val vote = it.voteAverage
            val overview= it.overview
            val language = it.originalLanguage
            if (language == "en"){
                languages = "English"
            }
            Glide.with(this).load(imageBig).into(imgBigDetail)
            Glide.with(this).load(imgSmall).into(imgSmallDetail)
            tvTitleDetail.text = title
            tvDateDetail.text = date
            tvVoteDetail.text = vote.toString()
            tvOverviewDetail.text = overview
            tvLanguageDetail.text = languages
        }*/


    }

    private fun showDetail(movieDetail: MovieDetail) {
        context?.let { Glide.with(it).load(movieDetail.getImageBackdropPath()).into(imgBigDetail) }
        context?.let { Glide.with(it).load(movieDetail.getImagePosterPath()).into(imgSmallDetail) }
        tvTitleDetail.text = movieDetail.title
    }

}