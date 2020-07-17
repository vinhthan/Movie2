package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.top_rated_detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieDetail
import com.thanmanhvinh.movieandnews.data.api.MovieReview
import com.thanmanhvinh.movieandnews.data.api.MovieSimilar
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickNowPlaying
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.adapter.CountryDetailAdapter
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.adapter.GenresDetailAdapter
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.adapter.ReviewDetailAdapter
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.adapter.SimilarAdapter
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import com.thanmanhvinh.movieandnews.utils.extensions.decimalFormat
import com.thanmanhvinh.movieandnews.utils.extensions.loadUrl
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.include_detail.*
import java.text.DecimalFormat

class TopRatedDetailFragment : BaseFragment<TopRatedDetailViewModel>(), ItemOnClickNowPlaying {

    private var id = BehaviorSubject.create<Int>()
    private lateinit var mListGenres: MutableList<MovieDetail.Genre>
    private lateinit var mAdapterGenres: GenresDetailAdapter
    private lateinit var mListCountries: MutableList<MovieDetail.ProductionCountry>
    private lateinit var mAdapterCountries: CountryDetailAdapter
    private lateinit var mListReview: MutableList<MovieReview.Result>
    private lateinit var mAdapterReview: ReviewDetailAdapter
    private lateinit var mListSimilar: MutableList<MovieSimilar.Result>
    private lateinit var mAdapterSimilar: SimilarAdapter

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
                }

            listCountries.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapterCountries.updateCountries(list)
                }

            listReview.observeOn(schedulerProvider.ui)
                .subscribe { listReview ->
                    mAdapterReview.updateReview(listReview)
                }

            listSimilar.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapterSimilar.updateSimilar(list)
                }

            errorToast.observeOn(schedulerProvider.ui)
                .subscribe {
                    Toast.makeText(context, R.string.please_try_again, Toast.LENGTH_SHORT).show()
                }
        }.addToDisposable()

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
        showGenres()
        showCountries()
        showReview()
        showSimilar()
    }

    private fun showDetail(movieDetail: MovieDetail) {
        imgBigDetail.loadUrl(movieDetail.getImageBackdropPath())
        imgSmallDetail.loadUrl(movieDetail.getImagePosterPath())
        tvTitleDetail.text = movieDetail.title
        val language = movieDetail.originalLanguage
        if (language == AppConstants.EN){
            tvLanguageDetail.text = getText(R.string.english)
        }else if (language == AppConstants.HI){
            tvLanguageDetail.text = getText(R.string.hindi)
        }
        tvDateDetail.text = movieDetail.releaseDate
        var runtime = movieDetail.runtime
        var h = runtime / 60
        var m = runtime % 60
        tvH.text = h.toString()
        tvM.text = m.toString()
        tvOverviewDetail.text = movieDetail.overview
        tvVoteDetail.text = movieDetail.voteAverage.toString()
        tvRevenue.text = movieDetail.revenue.decimalFormat()

        //View all and Collapse
        if(tvOverviewDetail.lineCount <= 3){
            tvViewAllOverview.visibility = View.GONE
            tvCollapseOverview.visibility = View.GONE
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

    private fun showReview(){
        mListReview = mutableListOf()
        mAdapterReview = ReviewDetailAdapter(context, mListReview)
        rcyReview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcyReview.adapter = mAdapterReview
    }

    private fun showSimilar(){
        mListSimilar = mutableListOf()
        mAdapterSimilar = SimilarAdapter(context, mListSimilar, this)
        rcySimilar.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcySimilar.adapter = mAdapterSimilar
    }

    override fun OnItemClickNowPlaying(position: Int) {
        val bundle = Bundle()
        val movieId = mListSimilar[position].id
        bundle.putInt(AppConstants.ID_MOVIE, movieId)
        findNavController().navigate(R.id.topRatedDetailFragment, bundle)
    }

}