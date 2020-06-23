package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.popular_detail

import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MoviePopular
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import kotlinx.android.synthetic.main.include_detail.*


class PopularDetailFragment : BaseFragment<PopularDetailViewModel>() {
    override fun createViewModel(): Class<PopularDetailViewModel> = PopularDetailViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_popular_detail

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {
/*        val output = mViewModel.transform(
            Any()
        )

        with(output){
            detailPopular.observeOn(schedulerProvider.ui)
                .subscribe {
                    showDetailPopular(it)
                }.addToDisposable()
        }*/

    }

    override fun initData() {

        val bundle = arguments?.getSerializable("MOVIE_POPULAR_DETAIL")
        val moviePopular = bundle?.let {
            bundle as MoviePopular.Result
        }

        moviePopular?.let {
            val imgBig = it.getImageBackdropPathPopular()
            val imgSmall = it.getImagePosterPathPopular()
            val date = it.releaseDate
            val vote = it.voteAverage
            val title = it.title
            val popularity = it.popularity
            val overview = it.overview

            Glide.with(this).load(imgBig).into(imgBigDetail)
            Glide.with(this).load(imgSmall).into(imgSmallDetail)
            tvTitleDetail.text = title
            tvDateDetail.text = date
            tvVoteDetail.text = vote.toString()
            tvOverviewDetail.text = overview
            tvPopularityDetail.text = popularity.toString()

/*            Glide.with(this).load(imgBig).into(imgBigPopular)
            Glide.with(this).load(imgSmall).into(imgSmallPopular)
            tvDatePopular.text = date
            tvVotePopular.text = vote.toString()
            tvTitlePopular.text = title
            tvPopularPopularity.text = popularity.toString()
            tvOverviewPopular.text = overview*/


        }


    }


}