package com.thanmanhvinh.movieandnews.ui.main.movie.see_all.top_rated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieTopRated
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickTopRated
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import com.thanmanhvinh.movieandnews.utils.recyclerview.PageIndicator
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_see_all_top_rated.*
import kotlinx.android.synthetic.main.include_toolbar.*


class SeeAllTopRatedFragment : BaseFragment<SeeAllTopRatedViewModel>(), PageIndicator, ItemOnClickTopRated {

    override var triggerLoadMore: BehaviorSubject<Boolean> = BehaviorSubject.create()
    override var triggerRefresh: BehaviorSubject<Boolean> = BehaviorSubject.create()

    lateinit var mList: MutableList<MovieTopRated.Results>
    lateinit var mAdapter: SeeAllTopRatedAdapter

    override fun createViewModel(): Class<SeeAllTopRatedViewModel> = SeeAllTopRatedViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_see_all_top_rated

    override fun getTitleActionBar(): Int = R.string.top_rated

    override fun bindViewModel() {
        val output = mViewModel.transform(
            SeeAllTopRatedViewModel.Input(
                triggerRefresh, triggerLoadMore
            )
        )
        with(output){
            listMovie.subscribeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mList.addAll(list)
                    mAdapter.updateListSeeAll(list)
                }.addToDisposable()
        }

    }

    override fun initData() {
        showTopRated()
        imgBackTopRatedSeeAll.setOnClickListener {
            onButtonBackClick()
        }
    }

    private fun showTopRated(){
        mList = mutableListOf()
        mAdapter = SeeAllTopRatedAdapter(context, this)
        rcySeeAllTopRated.setHasFixedSize(true)
        rcySeeAllTopRated.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        rcySeeAllTopRated.adapter = mAdapter
        rcySeeAllTopRated.initLoadMore(refreshSeeAllTopRated, this)
    }

    override fun OnItemClickTopRated(position: Int) {
        val bundle = Bundle()
        if (mList.size > 0){
            val movie = mList[position]
            val id = movie.id
            bundle.putInt(AppConstants.ID_MOVIE, id)
        }
        findNavController().navigate(R.id.topRatedDetailFragment, bundle)
    }


}