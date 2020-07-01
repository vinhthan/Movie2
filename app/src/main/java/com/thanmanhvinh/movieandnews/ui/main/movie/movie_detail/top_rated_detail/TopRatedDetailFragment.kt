package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.top_rated_detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieDetail
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.adapter.CountryDetailAdapter
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.adapter.GenresDetailAdapter
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.include_detail.*

class TopRatedDetailFragment : BaseFragment<TopRatedDetailViewModel>() {

    private var id = BehaviorSubject.create<Int>()
    private lateinit var mListGenres: MutableList<MovieDetail.Genre>
    private lateinit var mAdapterGenres: GenresDetailAdapter
    private lateinit var mListCountries: MutableList<MovieDetail.ProductionCountry>
    private lateinit var mAdapterCountries: CountryDetailAdapter

    override fun createViewModel(): Class<TopRatedDetailViewModel> = TopRatedDetailViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_top_rated_detailragment

    override fun getTitleActionBar(): Int = R.string.empty

    override var showToolBar: Boolean = false

    override fun bindViewModel() {

        val output = mViewModel.transform(
            TopRatedDetailViewModel.Input(
                id
            )
        )

        val movieId = arguments?.getInt(AppConstants.ID_MOVIE)
        movieId?.let {
            id.onNext(it)
        }

        with(output){
            detail.observeOn(schedulerProvider.ui)
                .subscribe {
                    showDetail(it)
                }

            listGenres.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapterGenres.upDateGenres(list)
                }.addToDisposable()

            listCountries.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapterCountries.updateCountries(list)
                }.addToDisposable()
        }

        imgBackDetail.setOnClickListener {
            onButtonBackClick()
        }

        imgPlayDetail.setOnClickListener {
            val bundleId = Bundle()
            if (movieId != null){
                bundleId.putInt(AppConstants.ID_MOVIE, movieId)
                findNavController().navigate(R.id.videoFragment, bundleId)
            }
        }

    }

    override fun initData() {
        showViewAllOverview()
        showGenres()
        showCountries()
    }

    private fun showDetail(movieDetail: MovieDetail) {
        context?.let { Glide.with(it).load(movieDetail.getImageBackdropPath()).into(imgBigDetail) }
        context?.let { Glide.with(it).load(movieDetail.getImagePosterPath()).into(imgSmallDetail) }
        tvTitleDetail.text = movieDetail.title
        val language = movieDetail.originalLanguage
        if (language == "en"){
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

    private fun showViewAllOverview(){
        var lineCount: Int = tvOverviewDetail.lineCount
        if (lineCount == 3){
            tvViewAllOverviewDetail.visibility = View.GONE
        }
        tvOverviewDetail.maxLines = 3
        tvViewAllOverviewDetail.setOnClickListener {
            tvOverviewDetail.maxLines = Int.MAX_VALUE
            tvViewAllOverviewDetail.visibility = View.GONE
        }
    }

    private fun showGenres(){
        mListGenres = mutableListOf()
        mAdapterGenres = GenresDetailAdapter(context, mListGenres)
        rcyGenresDetail.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcyGenresDetail.adapter = mAdapterGenres
    }

    private fun showCountries(){
        mListCountries = mutableListOf()
        mAdapterCountries = CountryDetailAdapter(context, mListCountries)
        rcyCountryDetail.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcyCountryDetail.adapter = mAdapterCountries
    }

}