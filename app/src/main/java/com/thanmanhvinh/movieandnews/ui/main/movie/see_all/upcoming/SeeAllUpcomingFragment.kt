package com.thanmanhvinh.movieandnews.ui.main.movie.see_all.upcoming

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieUpcoming
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickUpcoming
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import com.thanmanhvinh.movieandnews.utils.recyclerview.PageIndicator
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_see_all_upcoming.*
import kotlinx.android.synthetic.main.include_toolbar.*
import java.lang.Appendable


class SeeAllUpcomingFragment : BaseFragment<SeeAllUpcomingViewModel>(), PageIndicator, ItemOnClickUpcoming {

    override var triggerLoadMore: BehaviorSubject<Boolean> = BehaviorSubject.create()
    override var triggerRefresh: BehaviorSubject<Boolean> = BehaviorSubject.create()

    lateinit var mList: MutableList<MovieUpcoming.Result>
    lateinit var mAdapter: SeeAllUpcomingAdapter

    override fun createViewModel(): Class<SeeAllUpcomingViewModel> = SeeAllUpcomingViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_see_all_upcoming

    override fun getTitleActionBar(): Int = R.string.upcoming

    override fun bindViewModel() {
        val output = mViewModel.transform(
            SeeAllUpcomingViewModel.Input(
                triggerRefresh, triggerLoadMore
            )
        )

        with(output){
            listUpcoming.subscribeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mList.addAll(list)
                    mAdapter.updateListSeeAll(list)
                }.addToDisposable()
        }
    }

    override fun initData() {
        showSeeAllUpcoming()
    }

    private fun showSeeAllUpcoming(){
        mList = mutableListOf()
        mAdapter = SeeAllUpcomingAdapter(context, this)
        rcySeeAllUpcoming.setHasFixedSize(true)
        rcySeeAllUpcoming.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        rcySeeAllUpcoming.adapter = mAdapter
        rcySeeAllUpcoming.initLoadMore(refreshSeeAllUpcoming, this)
    }

    override fun OnItemClickUpcoming(position: Int) {
        val bundle = Bundle()
        if (mList.size > 0){
            val movie = mList[position]
            val id = movie.id
            bundle.putInt(AppConstants.ID_MOVIE, id)
        }
        findNavController().navigate(R.id.upcomingDetailFragment, bundle)
    }


}