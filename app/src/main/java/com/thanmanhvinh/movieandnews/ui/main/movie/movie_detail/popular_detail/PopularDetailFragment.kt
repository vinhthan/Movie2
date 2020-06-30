package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.popular_detail

import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieDetail
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.include_detail.*


class PopularDetailFragment : BaseFragment<PopularDetailViewModel>() {

    private var id = BehaviorSubject.create<Int>()

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
                }.addToDisposable()
        }

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

    }

    override fun initData() {

    }

    private fun showDetail(movie: MovieDetail){
        context?.let { Glide.with(it).load(movie.getImageBackdropPath()).into(imgBigDetail) }
        context?.let { Glide.with(it).load(movie.getImagePosterPath()).into(imgSmallDetail) }
        tvTitleDetail.text = movie.title
    }


}