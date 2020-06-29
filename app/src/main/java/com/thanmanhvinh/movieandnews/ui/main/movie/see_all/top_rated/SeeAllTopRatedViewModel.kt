package com.thanmanhvinh.movieandnews.ui.main.movie.see_all.top_rated

import com.thanmanhvinh.movieandnews.data.api.MovieTopRated
import com.thanmanhvinh.movieandnews.data.api.MovieTopRatedRequestPage
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class SeeAllTopRatedViewModel: BaseViewModel<SeeAllTopRatedViewModel.Input, SeeAllTopRatedViewModel.Output>() {
    private var page = AppConstants.LIST_PAGE
    private var limit = AppConstants.LIST_LIMIT

    data class Input (
        val triggerRefresh: Observable<Boolean>,
        val triggerLoadMore: Observable<Boolean>
    )

    data class Output (
        val listMovie: Observable<MutableList<MovieTopRated.Results>>
    )

    override fun transform(input: Input): Output {
        val mListMovie = BehaviorSubject.create<MutableList<MovieTopRated.Results>>()

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
                    mListMovie.onNext(list)
                }
            }.addToDisposable()


        return Output(mListMovie)
    }

    private fun doGetTopRatedPage(apiKey: String, page: Int): Observable<MovieTopRated>{
        return mDataManager.doGetTopRatedPage(MovieTopRatedRequestPage(apiKey, page))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

}