package com.thanmanhvinh.movieandnews.ui.main.movie

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
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
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.include_toolbar.*


@Suppress("UNREACHABLE_CODE")
class MovieFragment : BaseFragment<MovieViewModel>(), ItemOnClickNowPlaying, ItemOnClickPopular,
    ItemOnClickTopRated, ItemOnClickUpcoming {

    private val triggerLogout = BehaviorSubject.create<Unit>()

    private lateinit var sendToken: String
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

    override fun getTitleActionBar(): Int = R.string.discover

    override fun bindViewModel() {

        val ouput = mViewModel.transform(
            MovieViewModel.Input(
                triggerLogout = triggerLogout
            )
        )

        with(ouput) {
            listNowPlaying.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapterNowPlaying.updateList(list)
                }

            listUpcoming.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapterUpcoming.updateList(list)
                }

            listPopular.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapterPopular.updateList(list)
                }

            listTopRated.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapterTopRated.updateList(list)
                }

            token.observeOn(schedulerProvider.ui)
                .subscribe { tokens ->
                    tokens.requestToken.let { tok ->
                        sendToken = tok
                        /*tvLogin.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putString(AppConstants.TOKEN, sendToken)
                            findNavController().navigate(R.id.loginFragment, bundle)
                            //Toast.makeText(context, "click login", Toast.LENGTH_SHORT).show()
                        }*/
                    }
                }
            errorToast.observeOn(schedulerProvider.ui)
                .subscribe {
                    Toast.makeText(context, R.string.please_try_again, Toast.LENGTH_SHORT).show()
                }

        }.addToDisposable()

        /**
         * menu
         */
        imgMenu.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(context, imgMenu)
            popupMenu.menuInflater.inflate(R.menu.menu_right, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {item ->
                when(item.itemId){
                    R.id.logins ->{
                        val bundle = Bundle()
                        bundle.putString(AppConstants.TOKEN, sendToken)
                        findNavController().navigate(R.id.loginFragment, bundle)
                        //Toast.makeText(context, "click login", Toast.LENGTH_SHORT).show()
                    }
                    R.id.logout ->{
                        mViewModel.triggerLogout()
                        val bundle = Bundle()
                        bundle.putString(AppConstants.TOKEN, sendToken)
                        findNavController().navigate(R.id.loginFragment, bundle)
                        //Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            })
            popupMenu.show()
        }

    }

    override fun initData() {
        showMovieNowPlaying()
        showMovieUpcoming()
        showMoviePopular()
        showMovieTopRated()


        //
        tvSeeAllNowPlaying.setOnClickListener {
            findNavController().navigate(R.id.seeAllNowPlayingFragment)
        }
        tvSeeAllPopular.setOnClickListener {
            findNavController().navigate(R.id.seeAllPopularFragment)
        }
        tvSeeAllTopRated.setOnClickListener {
            findNavController().navigate(R.id.seeAllTopRatedFragment)
        }
        tvSeeAllUpcoming.setOnClickListener {
            findNavController().navigate(R.id.seeAllUpcomingFragment)
        }

        //
        imgSearch.setOnClickListener {
            findNavController().navigate(R.id.movieSearchFragment)
        }


    }

    private fun showMovieNowPlaying() {
        listMovieNowPlaying = mutableListOf()
        mAdapterNowPlaying = MovieNowPlayingAdapter(context, listMovieNowPlaying, this)
        rcyNowPlaying.setHasFixedSize(true)
        rcyNowPlaying.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        rcyNowPlaying.adapter = mAdapterNowPlaying
    }

    private fun showMovieUpcoming() {
        listMovieUpcoming = mutableListOf()
        mAdapterUpcoming = MovieUpcomingAdapter(context, listMovieUpcoming, this)
        rcyUpcoming.setHasFixedSize(true)
        rcyUpcoming.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        rcyUpcoming.adapter = mAdapterUpcoming
    }

    private fun showMoviePopular() {
        listMoviePopular = mutableListOf()
        mAdapterPopular = MoviePopularAdapter(context, listMoviePopular, this)
        rcyPopular.setHasFixedSize(true)
        rcyPopular.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcyPopular.adapter = mAdapterPopular
    }

    private fun showMovieTopRated() {
        listMovieTopRated = mutableListOf()
        mAdapterTopRated = MovieTopRatedAdapter(context, listMovieTopRated, this)
        rcyTopRated.setHasFixedSize(true)
        rcyTopRated.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcyTopRated.adapter = mAdapterTopRated
    }

    override fun OnItemClickNowPlaying(position: Int) {
/*        val movieNowPlayingDetail: MovieNowPlaying.Results = listMovieNowPlaying[position]
        val bundle = Bundle()
        bundle.putSerializable(AppConstants.MOVIE_NOW_PLAYING_DETAIL, movieNowPlayingDetail)*/
        val bundle = Bundle()
        if (listMovieNowPlaying.size > 0) {
            val movieNowPlaying = listMovieNowPlaying[position]
            val id = movieNowPlaying.id
            bundle.putInt(AppConstants.ID_MOVIE, id)
        }
        findNavController().navigate(R.id.nowPlayingDetailFragment, bundle)
    }

    override fun OnItemClickPopular(position: Int) {
        val bundle = Bundle()
        if (listMoviePopular.size > 0) {
            val moviePopular = listMoviePopular[position]
            val id = moviePopular.id
            bundle.putInt(AppConstants.ID_MOVIE, id)
        }
        findNavController().navigate(R.id.popularDetailFragment, bundle)
    }

    override fun OnItemClickTopRated(position: Int) {
        val bundle = Bundle()
        if (listMovieTopRated.size > 0) {
            val movieTopRated = listMovieTopRated[position]
            val id = movieTopRated.id
            bundle.putInt(AppConstants.ID_MOVIE, id)
        }
        findNavController().navigate(R.id.topRatedDetailFragment, bundle)
    }

    override fun OnItemClickUpcoming(position: Int) {
        val bundle = Bundle()
        if (listMovieUpcoming.size > 0) {
            val movieUpcoming = listMovieUpcoming[position]
            val id = movieUpcoming.id
            bundle.putInt(AppConstants.ID_MOVIE, id)
        }
        findNavController().navigate(R.id.upcomingDetailFragment, bundle)
    }




}


