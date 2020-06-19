package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.popular_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MoviePopular
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_popular_detail.*


class PopularDetailFragment : BaseFragment<PopularDetailViewModel>() {
    override fun createViewModel(): Class<PopularDetailViewModel> = PopularDetailViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_popular_detail

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {
        val output = mViewModel.transform(
            Any()
        )

        with(output){
            detailPopular.observeOn(schedulerProvider.ui)
                .subscribe {
                    showDetailPopular(it)
                }.addToDisposable()
        }

    }

    override fun initData() {

    }

    private fun showDetailPopular(detailPopular: MoviePopular.Result){
        tvTitleDetailPopular.text = detailPopular.title
        tvVoteCount.text = detailPopular.voteCount.toString()
    }


}