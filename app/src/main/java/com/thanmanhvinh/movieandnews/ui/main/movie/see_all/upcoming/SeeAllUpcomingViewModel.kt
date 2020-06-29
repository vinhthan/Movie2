package com.thanmanhvinh.movieandnews.ui.main.movie.see_all.upcoming

import com.thanmanhvinh.movieandnews.data.api.MovieUpcoming
import com.thanmanhvinh.movieandnews.data.api.MovieUpcomingRequestPage
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class SeeAllUpcomingViewModel : BaseViewModel<SeeAllUpcomingViewModel.Input, SeeAllUpcomingViewModel.Output>() {

    private var page = AppConstants.LIST_PAGE
    private val limit = AppConstants.LIST_LIMIT

    data class Input(
        val triggerRefresh: Observable<Boolean>,
        val triggerLoadMore: Observable<Boolean>
    )

    data class Output(
        val listUpcoming: Observable<MutableList<MovieUpcoming.Result>>
    )

    override fun transform(input: Input): Output {
        val mListUpcoming = BehaviorSubject.create<MutableList<MovieUpcoming.Result>>()

        val refresh = input.triggerRefresh.filter { it }.map {
            page = 1
        }
        val loadMore = input.triggerLoadMore.filter { it }.map {
            page++
        }

        Observable.merge(refresh, loadMore)
            .switchMap {
                doGetTopRatedPage(AppConstants.API_KEY, page)
            }.subscribe { result ->
                result.results.let { list ->
                    mListUpcoming.onNext(list)
                }
            }.addToDisposable()

        return Output(mListUpcoming)
    }

    private fun doGetTopRatedPage(apiKey: String, page: Int): Observable<MovieUpcoming>{
        return mDataManager.doGetUpcomingPage(MovieUpcomingRequestPage(apiKey, page))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

}