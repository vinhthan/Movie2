package com.thanmanhvinh.movieandnews.ui.main.movie.see_all.now_playing

import com.thanmanhvinh.movieandnews.data.api.MovieNowPlaying
import com.thanmanhvinh.movieandnews.data.api.MovieNowPlayingRequestPage
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.BehaviorSubject
import java.util.*

class SeeAllNowPlayingViewModel: BaseViewModel<SeeAllNowPlayingViewModel.Input, SeeAllNowPlayingViewModel.Output>() {

    private var page = AppConstants.LIST_PAGE

    data class Input (
        val triggerRefresh: Observable<Boolean>,
        val triggerLoadMore: Observable<Boolean>
    )

    data class Output (
        val listMovie: Observable<MutableList<MovieNowPlaying.Results>>
    )


    override fun transform(input: Input): Output {
        val mListMovie = BehaviorSubject.create<MutableList<MovieNowPlaying.Results>>()

        val refresh = input.triggerRefresh.filter { it }.map {
            page = 1
        }
        val loadMore = input.triggerLoadMore.filter { it }.map {
            page++
        }

        Observable.merge(refresh, loadMore)
            .switchMap {
                doGetNowPlayingPage(AppConstants.API_KEY, page)
            }.subscribe {
                it.results.let { list ->
                    mListMovie.onNext(list)
                }
            }.addToDisposable()


        return Output(mListMovie)
    }

    private fun doGetNowPlayingPage(apiKey: String, page: Int): Observable<MovieNowPlaying>{
        return mDataManager.doGetNowPlayingPage(MovieNowPlayingRequestPage(apiKey, page))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

}

