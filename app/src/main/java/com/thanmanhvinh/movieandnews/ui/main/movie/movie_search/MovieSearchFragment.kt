package com.thanmanhvinh.movieandnews.ui.main.movie.movie_search

import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChangeEvents
import com.jakewharton.rxbinding3.widget.textChanges
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieSearch
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickNowPlaying
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import kotlinx.android.synthetic.main.fragment_movie_search.*
import kotlinx.android.synthetic.main.include_toolbar.*
import java.util.concurrent.TimeUnit


class MovieSearchFragment : BaseFragment<MovieSearchViewModel>(), ItemOnClickNowPlaying {

    private lateinit var mList: MutableList<MovieSearch.Result>
    private lateinit var mAdapter: MovieSearchAdapter

    override fun createViewModel(): Class<MovieSearchViewModel> = MovieSearchViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_movie_search

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

        val output = mViewModel.transform(
            MovieSearchViewModel.Input(
                edtSearch.textChanges().map { it.toString() },
                imgSearchMovie.clicks().throttleFirst(300, TimeUnit.MILLISECONDS)
            )
        )

        with(output){
            loadList.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapter.updateListMovieSearch(list)
                    rcyMovieSearch.scrollToPosition(0)// Each search will return to the first position
                }.addToDisposable()
        }
    }

    override fun initData() {
        layout_search.visibility = View.VISIBLE
        layout_discover.visibility = View.GONE

        showMovieSearch()

        imgBack.setOnClickListener {
            onButtonBackClick()
        }

        tvCancel.setOnClickListener {
            edtSearch.setText("")
        }

        edtSearch.addTextChangedListener {
            imgSearchMovie.visibility = View.VISIBLE
            tvCancel.visibility = View.VISIBLE
        }

    }

    private fun showMovieSearch(){
        mList = mutableListOf()
        mAdapter = MovieSearchAdapter(context, mList, this)
        rcyMovieSearch.setHasFixedSize(true)
        rcyMovieSearch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcyMovieSearch.adapter = mAdapter

    }

    override fun OnItemClickNowPlaying(position: Int) {
        val bundle = Bundle()
        if (mList.size > 0){
            val movieId = mList[position].id
            bundle.putInt(AppConstants.ID_MOVIE, movieId)
        }
        findNavController().navigate(R.id.nowPlayingDetailFragment, bundle)
    }


}