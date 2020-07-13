package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.popular_detail

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
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.include_detail.*
import java.text.DecimalFormat


class PopularDetailFragment : BaseFragment<PopularDetailViewModel>(), ItemOnClickNowPlaying {

    private var id = BehaviorSubject.create<Int>()

    private lateinit var mListGenres: MutableList<MovieDetail.Genre>
    private lateinit var mListCountries: MutableList<MovieDetail.ProductionCountry>
    private lateinit var mAdapterGenres: GenresDetailAdapter
    private lateinit var mAdapterCountry: CountryDetailAdapter
    private lateinit var mListReview: MutableList<MovieReview.Result>
    private lateinit var mAdapterReview: ReviewDetailAdapter
    private lateinit var mListSimilar: MutableList<MovieSimilar.Result>
    private lateinit var mAdapterSimilar: SimilarAdapter

    override fun createViewModel(): Class<PopularDetailViewModel> = PopularDetailViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_popular_detail

    override fun getTitleActionBar(): Int = R.string.empty

    override var showToolBar: Boolean = false

    override fun bindViewModel() {

        val movieId = arguments?.getInt(AppConstants.ID_MOVIE)
        movieId?.let {
            id.onNext(it)
        }

        val output = mViewModel.transform(
            PopularDetailViewModel.Input(
                id
            )
        )

        with(output){
            detail.observeOn(schedulerProvider.ui)
                .subscribe { data ->
                    showDetail(data)
                }

            listGenres.observeOn(schedulerProvider.ui)
                .subscribe{list ->
                    mAdapterGenres.upDateGenres(list)
                }

            listCountries.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapterCountry.updateCountries(list)
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

/*        val output = mViewModel.transform(
            Any()
        )

        with(output){
            detailPopular.observeOn(schedulerProvider.ui)
                .subscribe {
                    showDetailPopular(it)
                }.addToDisposable()
        }*/

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

    private fun showDetail(movieDetail: MovieDetail){
        context?.let { Glide.with(it).load(movieDetail.getImageBackdropPath()).into(imgBigDetail) }
        context?.let { Glide.with(it).load(movieDetail.getImagePosterPath()).into(imgSmallDetail) }
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
        var revenue = movieDetail.revenue

        var pattern = "###,###,###,###.##"
        var decimalFormat = DecimalFormat(pattern)
        var format: String = decimalFormat.format(revenue)
        tvRevenue.text = format

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
        mAdapterCountry = CountryDetailAdapter(context, mListCountries)
        rcyCountryDetail.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcyCountryDetail.adapter = mAdapterCountry
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
        var movieId = mListSimilar[position].id
        bundle.putInt(AppConstants.ID_MOVIE, movieId)
        findNavController().navigate(R.id.popularDetailFragment, bundle)
    }


}