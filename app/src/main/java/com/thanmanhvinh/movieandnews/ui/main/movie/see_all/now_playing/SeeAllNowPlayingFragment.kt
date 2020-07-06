package com.thanmanhvinh.movieandnews.ui.main.movie.see_all.now_playing

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieNowPlaying
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickNowPlaying
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import com.thanmanhvinh.movieandnews.utils.recyclerview.PageIndicator
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_see_all_now_playing.*

class SeeAllNowPlayingFragment : BaseFragment<SeeAllNowPlayingViewModel>(), PageIndicator, ItemOnClickNowPlaying {

    override var triggerLoadMore: BehaviorSubject<Boolean> = BehaviorSubject.create()
    override var triggerRefresh: BehaviorSubject<Boolean> = BehaviorSubject.create()

    lateinit var mList: MutableList<MovieNowPlaying.Results>
    lateinit var mAdapter: SeeAllNowPlayingAdapter

    override fun createViewModel(): Class<SeeAllNowPlayingViewModel> = SeeAllNowPlayingViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_see_all_now_playing

    override fun getTitleActionBar(): Int = R.string.now_playing

    override fun bindViewModel() {
        val output = mViewModel.transform(
            SeeAllNowPlayingViewModel.Input(
                triggerRefresh, triggerLoadMore
            )
        )

        with(output){
            listMovie.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    /**
                     * right now mList have data
                     */
                    mList.addAll(list)
                    mAdapter.updateListSeeAll(list)
                }.addToDisposable()
        }
    }

    override fun initData() {
        showNowPlaying()
        imgBackNowPlayingSeeAll.setOnClickListener {
            onButtonBackClick()
        }
    }

    private fun showNowPlaying(){
        mList = mutableListOf()
        mAdapter = SeeAllNowPlayingAdapter(context, this)
        rcySeeAllNowPlaying.setHasFixedSize(true)
        rcySeeAllNowPlaying.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        rcySeeAllNowPlaying.adapter = mAdapter
        rcySeeAllNowPlaying.initLoadMore(refreshSeeAllNowPlaying, this)
    }

    override fun OnItemClickNowPlaying(position: Int) {
        val bundle = Bundle()
        if (mList.size > 0) {
            val movie = mList[position]
            val id = movie.id
            bundle.putInt(AppConstants.ID_MOVIE, id)
        }
        findNavController().navigate(R.id.nowPlayingDetailFragment, bundle)
        //Toast.makeText(context, "click $position", Toast.LENGTH_SHORT).show()
    }


}

