package com.thanmanhvinh.movieandnews.ui.main.movie

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieNowPlaying
import com.thanmanhvinh.movieandnews.data.api.MoviePopular
import com.thanmanhvinh.movieandnews.data.api.MovieTopRated
import com.thanmanhvinh.movieandnews.data.api.MovieUpcoming
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.base.BaseRecyclerView
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.*
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.include_toolbar.*


@Suppress("UNREACHABLE_CODE")
class MovieFragment : BaseFragment<MovieViewModel>(), ItemOnClickNowPlaying, ItemOnClickPopular,
    ItemOnClickTopRated, ItemOnClickUpcoming, ItemOnClickViewPager,
    BaseRecyclerView.ActionUserListener<MovieUpcoming.Result> {

    private val triggerLogout = BehaviorSubject.create<Unit>()
    private val handler = Handler()
/*    private var mList = intArrayOf(
        R.drawable.angry4,
        R.drawable.angry4,
        R.drawable.angry4,
        R.drawable.angry4,
        R.drawable.angry4,
        R.drawable.angry4
    )*/

    private lateinit var sendToken: String
    lateinit var listMovieNowPlaying: MutableList<MovieNowPlaying.Results>
    lateinit var listMovieUpcoming: MutableList<MovieUpcoming.Result>
    lateinit var listMoviePopular: MutableList<MoviePopular.Result>
    lateinit var listMovieTopRated: MutableList<MovieTopRated.Results>
    lateinit var mAdapterNowPlaying: MovieNowPlayingAdapter
    lateinit var mAdapterUpcoming: MovieUpcomingAdapter
    lateinit var mAdapterPopular: MoviePopularAdapter
    lateinit var mAdapterTopRated: MovieTopRatedAdapter
    lateinit var mAdapterViewPager: PagerAdapter

    override fun createViewModel(): Class<MovieViewModel> = MovieViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_movie

    override fun getTitleActionBar(): Int = R.string.discover

    override fun bindViewModel() {

        val output = mViewModel.transform(
            MovieViewModel.Input(
                triggerLogout = triggerLogout
            )
        )

        with(output) {
            listNowPlaying.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapterNowPlaying.updateList(list)
                }

            listUpcoming.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapterUpcoming.updateList(list)
                    mAdapterViewPager.updateItemMovie(list)
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
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.logins -> {
                        val bundle = Bundle()
                        bundle.putString(AppConstants.TOKEN, sendToken)
                        findNavController().navigate(R.id.loginFragment, bundle)
                    }
                    R.id.logout -> {
                        mViewModel.triggerLogout()
                        val bundle = Bundle()
                        bundle.putString(AppConstants.TOKEN, sendToken)
                        findNavController().navigate(R.id.loginFragment, bundle)
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
        showViewPager()


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

        //search movie
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

    override fun onItemClickNowPlaying(position: Int) {
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

    override fun onItemClickPopular(position: Int) {
        val bundle = Bundle()
        if (listMoviePopular.size > 0) {
            val moviePopular = listMoviePopular[position]
            val id = moviePopular.id
            bundle.putInt(AppConstants.ID_MOVIE, id)
        }
        findNavController().navigate(R.id.popularDetailFragment, bundle)
    }

    override fun onItemClickTopRated(position: Int) {
        val bundle = Bundle()
        if (listMovieTopRated.size > 0) {
            val movieTopRated = listMovieTopRated[position]
            val id = movieTopRated.id
            bundle.putInt(AppConstants.ID_MOVIE, id)
        }
        findNavController().navigate(R.id.topRatedDetailFragment, bundle)
    }

    override fun onItemClickUpcoming(position: Int) {
        val bundle = Bundle()
        if (listMovieUpcoming.size > 0) {
            val movieUpcoming = listMovieUpcoming[position]
            val id = movieUpcoming.id
            bundle.putInt(AppConstants.ID_MOVIE, id)
        }
        findNavController().navigate(R.id.upcomingDetailFragment, bundle)
    }

    /**
     * ViewPager2
     * link tham khao: https://itnext.io/android-viewpager2-tablayout-3099aae2f396
     */
    //api
    private fun showViewPager() {
        mAdapterViewPager = context?.let { PagerAdapter(it, listMovieUpcoming, this) }!!
        viewPagerMovie.adapter = mAdapterViewPager
        viewPagerMovie.scrollState
        //viewPagerMovie.offscreenPageLimit = 3
        //viewPagerMovie.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        //connect viewpager2 and tablayout
        TabLayoutMediator(
            tabLayoutMovie,
            viewPagerMovie,
            TabConfigurationStrategy { _, _ -> // Styling each tab here
                //tab.text = "Tab $position"
            }
        ).attach()

        viewPagerMovie.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(slideRunnable)
                handler.postDelayed(slideRunnable, 2000)
                //run continuously
                if (position == listMovieUpcoming.size - 1){
                    viewPagerMovie.currentItem = 0
                }
            }

        })
        viewPagerMovie.setPageTransformer(ViewPagerTransformation())
    }

    private val slideRunnable = Runnable {
        if (viewPagerMovie != null) {
            viewPagerMovie.currentItem = viewPagerMovie.currentItem + 1
        }
    }
    //animation viewpager2
    class ViewPagerTransformation: ViewPager2.PageTransformer{
        override fun transformPage(page: View, position: Float) {
            val absPos = Math.abs(position)
            page.apply {
                translationY = absPos * 500f
                translationX = absPos * 500f
                scaleX = 0.86f
                scaleY = 0.86f
            }
            when {
                position < -1 -> page.alpha = 0f
                position <= 1 -> page.alpha = Math.max(0f, 1 - Math.abs(position))
                else -> page.alpha = 0f
            }
        }

    }


    //offline
/*    private fun showViewPager(){
        mAdapterViewPager = context?.let { PagerAdapter(it) }!!
        viewPagerMovie.adapter = mAdapterViewPager
        TabLayoutMediator(
            tabLayoutMovie,
            viewPagerMovie,
            TabConfigurationStrategy { _, _ -> // Styling each tab here

            }
        ).attach()
    }*/


    override fun select(t: MovieUpcoming.Result?) {
        TODO("Not yet implemented")
    }

    override fun onItemClickViewPager(position: Int) {
        val bundle = Bundle()
        if (listMovieUpcoming.size > 0) {
            val movie = listMovieUpcoming[position]
            val id = movie.id
            bundle.putInt(AppConstants.ID_MOVIE, id)
        }
        findNavController().navigate(R.id.upcomingDetailFragment, bundle)
    }


}


