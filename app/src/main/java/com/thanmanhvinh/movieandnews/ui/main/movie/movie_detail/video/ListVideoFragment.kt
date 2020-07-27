package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.video

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieVideo
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickNowPlaying
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_list_video.*

class ListVideoFragment : BaseFragment<ListVideoViewModel>(), ItemOnClickNowPlaying {

    private var id: BehaviorSubject<Int> = BehaviorSubject.create()

    private lateinit var mList: MutableList<MovieVideo.Result>
    private lateinit var mAdapter: ListVideoAdapter

    override fun createViewModel(): Class<ListVideoViewModel> = ListVideoViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_list_video

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

        val movieId = arguments?.getInt(AppConstants.ID_MOVIE)
        movieId?.let {
            id.onNext(it)
        }

        val output = mViewModel.transform(
            ListVideoViewModel.Input(
                id
            )
        )

        with(output) {
            video.observeOn(schedulerProvider.ui)
                .subscribe { listVideo ->
                    mAdapter.updateVideo(listVideo)
                    if (listVideo.size == 0){
                        tvNotificationVideo.visibility = View.VISIBLE
                    }else{
                        tvNotificationVideo.visibility = View.GONE
                    }
                }.addToDisposable()
        }

    }

    override fun initData() {
        showListVideo()

    }

    private fun showListVideo() {
        mList = mutableListOf()
        mAdapter = ListVideoAdapter(context, mList, this)
        rcyVideo.setHasFixedSize(true)
        rcyVideo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcyVideo.adapter = mAdapter
    }

    override fun onItemClickNowPlaying(position: Int) {
        val bundle = Bundle()
        if (mList.size > 0) {
            val video = AppConstants.LINK_YOU_TOBE + mList[position].key
            bundle.putString(AppConstants.SEND_LINK, video)
        }
        findNavController().navigate(R.id.playVideoFragment, bundle)
    }

}