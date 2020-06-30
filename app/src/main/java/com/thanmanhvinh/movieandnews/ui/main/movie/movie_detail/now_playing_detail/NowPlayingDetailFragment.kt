package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.now_playing_detail

import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieDetail
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.adapter.GenresDetailAdapter
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.include_detail.*


class NowPlayingDetailFragment : BaseFragment<NowPlayingDetailViewModel>() {

    private var id = BehaviorSubject.create<Int>()
    private lateinit var mList: MutableList<MovieDetail.Genre>
    private lateinit var mAdapter: GenresDetailAdapter

    override fun createViewModel(): Class<NowPlayingDetailViewModel> =
        NowPlayingDetailViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_now_playing_detail

    override fun getTitleActionBar(): Int = R.string.empty

    override var showToolBar: Boolean = false

    override fun bindViewModel() {

        val movieId = arguments?.getInt(AppConstants.ID_MOVIE)
        movieId?.let {
            id.onNext(it)
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

            listGenres.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapter.upDateGenres(list)
                }.addToDisposable()
        }


        imgBackDetail.setOnClickListener {
            onButtonBackClick()
        }
    }

    override fun initData() {

        showGenres()


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
        val language = movieDetail.originalLanguage
        if (language == "en"){
            tvLanguageDetail.text = "English"
        }
        tvDateDetail.text = movieDetail.releaseDate
        var runtime = movieDetail.runtime
        var h = runtime / 60
        var m = runtime % 60
        tvH.text = h.toString()
        tvM.text = m.toString()
        tvOverviewDetail.text = movieDetail.overview
        tvVoteDetail.text = movieDetail.voteAverage.toString()

    }

    private fun showGenres(){
        mList = mutableListOf()
        mAdapter = GenresDetailAdapter(context, mList)
        rcyGenresDetail.setHasFixedSize(true)
        rcyGenresDetail.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        rcyGenresDetail.adapter = mAdapter
    }

}