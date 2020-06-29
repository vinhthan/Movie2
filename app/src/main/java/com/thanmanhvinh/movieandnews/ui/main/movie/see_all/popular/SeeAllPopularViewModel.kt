package com.thanmanhvinh.movieandnews.ui.main.movie.see_all.popular

import com.androidnetworking.error.ANError
import com.thanmanhvinh.movieandnews.data.api.MoviePopular
import com.thanmanhvinh.movieandnews.data.api.MoviePopularRequestPage
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class SeeAllPopularViewModel :
    BaseViewModel<SeeAllPopularViewModel.Input, SeeAllPopularViewModel.Ouput>() {

    private var page = AppConstants.LIST_PAGE
    private var limit = AppConstants.LIST_LIMIT

    data class Input(
        val triggerRefresh: Observable<Boolean>,
        val triggerLoadMore: Observable<Boolean>
    )

    data class Ouput(
        val listMovie: Observable<MutableList<MoviePopular.Result>>
    )

    override fun transform(input: Input): Ouput {
        val mList = BehaviorSubject.create<MutableList<MoviePopular.Result>>()

        val refresh = input.triggerRefresh.filter { it }.map {
            page = 1
        }
        val loadMore = input.triggerLoadMore.filter { it }.map {
            page++
        }

        Observable.merge(refresh, loadMore)
            .switchMap {
                doGetPopularPage(AppConstants.API_KEY, page)
            }.subscribe { result ->
                result.results.let { list ->
                    mList.onNext(list)
                }
            }.addToDisposable()


        return Ouput(mList)
    }

    private fun doGetPopularPage(apiKey: String, page: Int): Observable<MoviePopular>{
        return mDataManager.doGetPopularPage(MoviePopularRequestPage(apiKey, page))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

}