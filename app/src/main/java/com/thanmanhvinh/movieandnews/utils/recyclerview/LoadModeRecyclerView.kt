package com.thanmanhvinh.movieandnews.utils.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.thanmanhvinh.movieandnews.ui.main.movie.see_all.popular.SeeAllPopularFragment
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.subjects.BehaviorSubject

interface PageIndicator {
    var triggerLoadMore: BehaviorSubject<Boolean>
    var triggerRefresh: BehaviorSubject<Boolean>
}

class LoadMoreRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr), SwipeRefreshLayout.OnRefreshListener {
    var skipLoadMore: Boolean = true

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    private lateinit var triggerLoadMore: BehaviorSubject<Boolean>
    private lateinit var triggerRefresh: BehaviorSubject<Boolean>


    var endOfPage = false

    fun initLoadMore(swipeRefreshLayout: SwipeRefreshLayout, pageIndicator: PageIndicator) {
        skipLoadMore = false
        this.swipeRefreshLayout = swipeRefreshLayout
        this.swipeRefreshLayout.setOnRefreshListener(this)
        this.triggerLoadMore = pageIndicator.triggerLoadMore
        this.triggerRefresh = pageIndicator.triggerRefresh
        startRefresh()
    }

    /**
     * start refresh
     */
    private fun startRefresh() {
        endOfPage = false
        swipeRefreshLayout.isRefreshing = true
        triggerRefresh.onNext(true)
    }

    fun triggerRefresh(){
        if(isRefresh()){
            return
        }
        swipeRefreshLayout.isRefreshing = true
        triggerRefresh.onNext(true)
    }

    fun isRefresh(): Boolean {
        if (triggerRefresh.hasValue() && !skipLoadMore) {
            return triggerRefresh.value!!
        }
        return false
    }

    fun endRefresh() {

        swipeRefreshLayout.isRefreshing = false
        triggerRefresh.onNext(false)
    }

    /**
     * start load more
     */
    fun startLoadMore() {

        swipeRefreshLayout.isEnabled = false
        triggerLoadMore.onNext(true)
    }

    fun isLoadMore(): Boolean {
        if (triggerLoadMore.hasValue() && !skipLoadMore) {
            return triggerLoadMore.value!!
        }
        return false
    }

    fun endLoadMore() {

        triggerLoadMore.onNext(false)
        swipeRefreshLayout.isEnabled = true
    }

    override fun onRefresh() {
        endOfPage = false
        triggerRefresh.onNext(true)
    }

    /**
     * call when request API error
     */
    fun requestAPIError() {
        if (isRefresh()) {
            endRefresh()
        }
        if (isLoadMore()) {
            endLoadMore()
        }
    }


    fun checkEndOfPage(list: List<Any>, limit: Int = AppConstants.LIST_LIMIT){
        endOfPage = list.count() < limit
    }

}