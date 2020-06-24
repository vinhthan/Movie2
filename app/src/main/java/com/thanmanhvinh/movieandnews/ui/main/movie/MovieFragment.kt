package com.thanmanhvinh.movieandnews.ui.main.movie

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieNowPlaying
import com.thanmanhvinh.movieandnews.data.api.MoviePopular
import com.thanmanhvinh.movieandnews.data.api.MovieTopRated
import com.thanmanhvinh.movieandnews.data.api.MovieUpcoming
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.*
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import com.thanmanhvinh.movieandnews.utils.recyclerview.PageIndicator
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : BaseFragment<MovieViewModel>(), ItemOnClickNowPlaying, ItemOnClickPopular, ItemOnClickTopRated, ItemOnClickUpcoming, PageIndicator{

    override var triggerLoadMore: BehaviorSubject<Boolean> = BehaviorSubject.create()
    override var triggerRefresh: BehaviorSubject<Boolean> = BehaviorSubject.create()

    lateinit var listMovieNowPlaying: MutableList<MovieNowPlaying.Results>
    lateinit var listMovieUpcoming: MutableList<MovieUpcoming.Result>
    lateinit var listMoviePopular: MutableList<MoviePopular.Result>
    lateinit var listMovieTopRated: MutableList<MovieTopRated.Results>
    lateinit var mAdapterNowPlaying: MovieNowPlayingAdapter
    lateinit var mAdapterUpcoming: MovieUpcomingAdapter
    lateinit var mAdapterPopular: MoviePopularAdapter
    lateinit var mAdapterTopRated: MovieTopRatedAdapter


    override fun createViewModel(): Class<MovieViewModel> = MovieViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_movie

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

        val ouput = mViewModel.transform(
            MovieViewModel.Input(
                triggerLoadMore, triggerRefresh
            )
        )

        with(ouput){
            listNowPlaying.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapterNowPlaying.updateList(list)

                }.addToDisposable()

            listUpcoming.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapterUpcoming.UpdateList(list)
                }.addToDisposable()

            listPopular.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapterPopular.UpdateList(list)
                }.addToDisposable()

            listTopRated.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapterTopRated.UpdateList(list)
                }.addToDisposable()

        }
    }

    override fun initData() {
        showMovieNowPlaying()
        showMovieUpcoming()
        showMoviePopular()
        showMovieTopRated()

    }

    private fun showMovieNowPlaying(){
        listMovieNowPlaying = mutableListOf()
        mAdapterNowPlaying = MovieNowPlayingAdapter(context, this)
        rcyNowPlaying.setHasFixedSize(true)
        rcyNowPlaying.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        rcyNowPlaying.adapter = mAdapterNowPlaying
        rcyNowPlaying.initLoadMore(refresh, this)
    }

    private fun showMovieUpcoming(){
        listMovieUpcoming = mutableListOf()
        mAdapterUpcoming = MovieUpcomingAdapter(context, listMovieUpcoming, this)
        rcyUpcoming.setHasFixedSize(true)
        rcyUpcoming.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcyUpcoming.adapter = mAdapterUpcoming
    }

    private fun showMoviePopular(){
        listMoviePopular = mutableListOf()
        mAdapterPopular = MoviePopularAdapter(context, listMoviePopular, this)
        rcyPopular.setHasFixedSize(true)
        rcyPopular.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcyPopular.adapter = mAdapterPopular
    }

    private fun showMovieTopRated(){
        listMovieTopRated = mutableListOf()
        mAdapterTopRated = MovieTopRatedAdapter(context, listMovieTopRated, this)
        rcyTopRated.setHasFixedSize(true)
        rcyTopRated.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcyTopRated.adapter = mAdapterTopRated
    }

    

    override fun OnItemClickNowPlaying(position: MovieNowPlaying.Results) {
        val movieNowPlayingDetail: MovieNowPlaying.Results = position
        val bundle = Bundle()
        bundle.putSerializable(AppConstants.MOVIE_NOW_PLAYING_DETAIL, movieNowPlayingDetail)
        findNavController().navigate(R.id.nowPlayingDetailFragment, bundle)
    }

    override fun OnItemClickPopular(position: Int) {
        val moviePopularDetail: MoviePopular.Result = listMoviePopular[position]
        val bundle = Bundle()
        bundle.putSerializable(AppConstants.MOVIE_POPULAR_DETAIL, moviePopularDetail)
        findNavController().navigate(R.id.popularDetailFragment, bundle)
    }

    override fun OnItemClickTopRated(position: Int) {
        val movieTopRated: MovieTopRated.Results = listMovieTopRated[position]
        val bundle = Bundle()
        bundle.putSerializable(AppConstants.MOVIE_TOP_RATED_DETAIL, movieTopRated)
        findNavController().navigate(R.id.topRatedDetailFragment, bundle)
    }

    override fun OnItemClickUpcoming(position: Int) {
        val movieUpcoming: MovieUpcoming.Result = listMovieUpcoming[position]
        val bundle = Bundle()
        bundle.putSerializable(AppConstants.MOVIE_UPCOMING_DETAIL, movieUpcoming)
        findNavController().navigate(R.id.upcomingDetailFragment, bundle)
    }



}


