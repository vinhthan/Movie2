package com.thanmanhvinh.movieandnews.ui.main.movie.see_all.popular

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MoviePopular
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickPopular
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import com.thanmanhvinh.movieandnews.utils.recyclerview.PageIndicator
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_see_all_popular.*


class SeeAllPopularFragment : BaseFragment<SeeAllPopularViewModel>(), PageIndicator, ItemOnClickPopular {

    override var triggerLoadMore: BehaviorSubject<Boolean> = BehaviorSubject.create()
    override var triggerRefresh: BehaviorSubject<Boolean> = BehaviorSubject.create()

    private lateinit var mList: MutableList<MoviePopular.Result>
    private lateinit var mAdapter: SeeAllPopularAdapter


    override fun createViewModel(): Class<SeeAllPopularViewModel> = SeeAllPopularViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_see_all_popular

    override fun getTitleActionBar(): Int = R.string.popular

    override fun bindViewModel() {
        val output = mViewModel.transform(
            SeeAllPopularViewModel.Input(
                triggerRefresh, triggerLoadMore
            )
        )
        with(output){
            listMovie.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mList.addAll(list)
                    mAdapter.updateListSeeAll(list)
                }.addToDisposable()
        }
    }

    override fun initData() {
        showSeeAllPopular()
    }

    private fun showSeeAllPopular(){
        mList = mutableListOf()
        mAdapter = SeeAllPopularAdapter(context, this)
        rcySeeAllPopular.setHasFixedSize(true)
        rcySeeAllPopular.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        rcySeeAllPopular.adapter = mAdapter
        rcySeeAllPopular.initLoadMore(refreshSeeAllPopular, this)
    }

    override fun OnItemClickPopular(position: Int) {
        val bundle = Bundle()
        if (mList.size > 0){
            val movie = mList[position]
            val id = movie.id
            bundle.putInt(AppConstants.ID_MOVIE, id)
        }
        findNavController().navigate(R.id.popularDetailFragment, bundle)
    }


}