package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.top_rated_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieDetail
import com.thanmanhvinh.movieandnews.data.api.MovieTopRated
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_popular_detail.*
import kotlinx.android.synthetic.main.fragment_top_rated_detailragment.*
import kotlinx.android.synthetic.main.include_detail.*

class TopRatedDetailFragment : BaseFragment<TopRatedDetailViewModel>() {

    private var id = BehaviorSubject.create<Int>()

    override fun createViewModel(): Class<TopRatedDetailViewModel> = TopRatedDetailViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_top_rated_detailragment

    override fun getTitleActionBar(): Int = R.string.empty

    override var showToolBar: Boolean = false

    override fun bindViewModel() {
        imgBackDetail.setOnClickListener {
            onButtonBackClick()
        }

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
        }
    }

    override fun initData() {

    }

    private fun showDetail(movieDetail: MovieDetail) {
        context?.let { Glide.with(it).load(movieDetail.getImageBackdropPath()).into(imgBigDetail) }
        context?.let { Glide.with(it).load(movieDetail.getImagePosterPath()).into(imgSmallDetail) }
        tvTitleDetail.text = movieDetail.title
    }

}