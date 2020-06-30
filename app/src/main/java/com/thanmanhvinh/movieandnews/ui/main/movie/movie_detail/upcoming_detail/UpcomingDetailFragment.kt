package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.upcoming_detail

import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieDetail
import com.thanmanhvinh.movieandnews.data.api.MovieUpcoming
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_upcoming_detail.*
import kotlinx.android.synthetic.main.include_detail.*


class UpcomingDetailFragment : BaseFragment<UpcomingDetailViewModel>() {

    private var id = BehaviorSubject.create<Int>()

    override fun createViewModel(): Class<UpcomingDetailViewModel> =
        UpcomingDetailViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_upcoming_detail

    override fun getTitleActionBar(): Int = R.string.empty

    override var showToolBar: Boolean = false

    override fun bindViewModel() {
        imgBackDetail.setOnClickListener {
            onButtonBackClick()
        }

        val output = mViewModel.transform(
            UpcomingDetailViewModel.Input(
                id
            )
        )

        val movieId = arguments?.getInt(AppConstants.ID_MOVIE)
        movieId?.let {
            id.onNext(it)
        }

        with(output){
            detail.subscribe {
                showDetail(it)
            }
        }


    }

    override fun initData() {

    }

    private fun showDetail(movie: MovieDetail){
        context?.let { Glide.with(it).load(movie.getImageBackdropPath()).into(imgBigDetail) }
        context?.let { Glide.with(it).load(movie.getImagePosterPath()).into(imgSmallDetail) }
        tvTitleDetail.text = movie.title
    }


}