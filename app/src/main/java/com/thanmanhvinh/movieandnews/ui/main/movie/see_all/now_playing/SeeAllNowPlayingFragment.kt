package com.thanmanhvinh.movieandnews.ui.main.movie.see_all.now_playing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieNowPlaying
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickNowPlaying
import com.thanmanhvinh.movieandnews.utils.recyclerview.PageIndicator
import io.reactivex.rxkotlin.toObservable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_see_all_now_playing.*

class SeeAllNowPlayingFragment : BaseFragment<SeeAllNowPlayingViewModel>(), PageIndicator {

    override var triggerLoadMore: BehaviorSubject<Boolean> = BehaviorSubject.create()
    override var triggerRefresh: BehaviorSubject<Boolean> = BehaviorSubject.create()

    lateinit var listNowPlaying: MutableList<MovieNowPlaying.Results>
    lateinit var mAdapter: SeeAllNowPlayingAdapter


    override fun createViewModel(): Class<SeeAllNowPlayingViewModel> = SeeAllNowPlayingViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_see_all_now_playing

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {
        val output = mViewModel.transform(
            SeeAllNowPlayingViewModel.Input(
                triggerRefresh, triggerLoadMore
            )
        )

        with(output){
            listMovie.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapter.updateListSeeAll(list)
                }.addToDisposable()

        }

    }

    override fun initData() {
        showNowPlaying()
    }

    private fun showNowPlaying(){
        listNowPlaying = mutableListOf()
        mAdapter = SeeAllNowPlayingAdapter(context)
        rcySeeAllNowPlaying.setHasFixedSize(true)
        rcySeeAllNowPlaying.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        rcySeeAllNowPlaying.adapter = mAdapter
        rcySeeAllNowPlaying.initLoadMore(refreshSeeAllNowPlaying, this)
    }


}

