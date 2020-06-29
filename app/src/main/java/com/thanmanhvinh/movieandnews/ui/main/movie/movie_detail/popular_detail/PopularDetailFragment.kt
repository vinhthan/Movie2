package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.popular_detail

import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MoviePopular
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import kotlinx.android.synthetic.main.include_detail.*


class PopularDetailFragment : BaseFragment<PopularDetailViewModel>() {
    override fun createViewModel(): Class<PopularDetailViewModel> = PopularDetailViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_popular_detail

    override fun getTitleActionBar(): Int = R.string.empty

    override var showToolBar: Boolean = false

    override fun bindViewModel() {

        imgBackDetail.setOnClickListener {
            onButtonBackClick()
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



    }

    override fun initData() {

        val bundle = arguments?.getSerializable(AppConstants.MOVIE_POPULAR_DETAIL)
        val moviePopular = bundle?.let {
            bundle as MoviePopular.Result
        }

        moviePopular?.let {
            var languages = ""
            val imgBig = it.getImageBackdropPathPopular()
            val imgSmall = it.getImagePosterPathPopular()
            val date = it.releaseDate
            val vote = it.voteAverage
            val title = it.title
            val overview = it.overview
            val language = it.originalLanguage
            if (language == "en"){
                languages = "English"
            }
            Glide.with(this).load(imgBig).into(imgBigDetail)
            Glide.with(this).load(imgSmall).into(imgSmallDetail)
            tvTitleDetail.text = title
            tvDateDetail.text = date
            tvVoteDetail.text = vote.toString()
            tvOverviewDetail.text = overview
            tvLanguageDetail.text = languages


        }


    }


}