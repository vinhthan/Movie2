package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.now_playing_detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieDetail
import com.thanmanhvinh.movieandnews.data.api.MovieReview
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.adapter.CountryDetailAdapter
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.adapter.GenresDetailAdapter
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.adapter.ReviewDetailAdapter
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.include_detail.*
import kotlinx.android.synthetic.main.item_review.*


class NowPlayingDetailFragment : BaseFragment<NowPlayingDetailViewModel>() {

    private var id = BehaviorSubject.create<Int>()
    private lateinit var mListGenres: MutableList<MovieDetail.Genre>
    private lateinit var mListCountries: MutableList<MovieDetail.ProductionCountry>
    private lateinit var mAdapterGenres: GenresDetailAdapter
    private lateinit var mAdapterCountries: CountryDetailAdapter
    private lateinit var mListReview: MutableList<MovieReview.Result>
    private lateinit var mAdapterReview: ReviewDetailAdapter

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
                }

            listGenres.observeOn(schedulerProvider.ui)
                .subscribe { listGenres ->
                    mAdapterGenres.upDateGenres(listGenres)
                }

            listCountries.observeOn(schedulerProvider.ui)
                .subscribe { listCountries ->
                    mAdapterCountries.updateCountries(listCountries)
                }

            listReview.observeOn(schedulerProvider.ui)
                .subscribe { listReview ->
                    mAdapterReview.updateReview(listReview)
                }

        }.addToDisposable()


        imgBackDetail.setOnClickListener {
            onButtonBackClick()
        }

        imgPlayDetail.setOnClickListener {
            val bundleId = Bundle()
            if (movieId != null){
                bundleId.putInt(AppConstants.ID_MOVIE, movieId)
            }
            findNavController().navigate(R.id.videoFragment, bundleId)
        }
    }

    override fun initData() {

        showGenres()
        showCountry()
        showViewAllOverview()
        showReview()


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
        if (language == AppConstants.EN){
            tvLanguageDetail.text = getText(R.string.english)
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
        mListGenres = mutableListOf()
        mAdapterGenres = GenresDetailAdapter(context, mListGenres)
        //rcyGenresDetail.setHasFixedSize(true)
        rcyGenresDetail.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcyGenresDetail.adapter = mAdapterGenres

    }

    private fun showCountry(){
        mListCountries = mutableListOf()
        mAdapterCountries = CountryDetailAdapter(context, mListCountries)
        rcyCountryDetail.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcyCountryDetail.adapter = mAdapterCountries
    }

    private fun showViewAllOverview(){
        var lineCount: Int = tvOverviewDetail.lineCount
        if (lineCount == 3){
            tvViewAllOverview.visibility = View.GONE
        }
        tvOverviewDetail.maxLines = 3
        tvViewAllOverview.setOnClickListener {
            tvOverviewDetail.maxLines = Int.MAX_VALUE
            tvViewAllOverview.visibility = View.GONE
            tvCollapseOverview.visibility = View.VISIBLE
        }
        tvCollapseOverview.setOnClickListener {
            tvOverviewDetail.maxLines = 3
            tvCollapseOverview.visibility= View.GONE
            tvViewAllOverview.visibility = View.VISIBLE
        }
    }

    private fun showReview(){
        mListReview = mutableListOf()
        mAdapterReview = ReviewDetailAdapter(context, mListReview)
        rcyReview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcyReview.adapter = mAdapterReview
    }

}