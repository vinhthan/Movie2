package com.thanmanhvinh.movieandnews.ui.main.movie

import android.util.Log
import com.thanmanhvinh.movieandnews.data.api.*
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class MovieViewModel : BaseViewModel<Any, MovieViewModel.Output>() {

/*    private var page = AppConstants.LIST_PAGE
    private var limit = AppConstants.LIST_LIMIT

    data class Input (
        val triggerLoadMore: Observable<Boolean>,
        val triggerRefresh: Observable<Boolean>
    )*/

    data class Output(
        val listNowPlaying: Observable<MutableList<MovieNowPlaying.Results>>,
        val listUpcoming: Observable<MutableList<MovieUpcoming.Result>>,
        val listPopular: Observable<MutableList<MoviePopular.Result>>,
        val listTopRated: Observable<MutableList<MovieTopRated.Results>>
    )



    override fun transform(input: Any): Output {
        val mListNowPlaying = BehaviorSubject.create<MutableList<MovieNowPlaying.Results>>()
        val mListUpcoming = BehaviorSubject.create<MutableList<MovieUpcoming.Result>>()
        val mListMoviePopular = BehaviorSubject.create<MutableList<MoviePopular.Result>>()
        val mListMovieTopRated = BehaviorSubject.create<MutableList<MovieTopRated.Results>>()

/*        val refresh = input.triggerRefresh.filter { it }.map {
            page = 1
        }
        val loadMore = input.triggerLoadMore.filter { it }.map {
            page++
        }*/

/*        //GetMovieNowPlaying
        Observable.merge(refresh, loadMore)
            .switchMap {
                doGetMovieNowPlaying(AppConstants.API_KEY, page)
            }.subscribe {
                it.results.let {list ->
                    mListNowPlaying.onNext(list)
                }
            }.addToDisposable()

        //GetMovieUpcoming
        Observable.merge(refresh, loadMore)
            .switchMap {
                doGetMovieUpcoming(AppConstants.API_KEY, page)
            }.subscribe {
                it.results.let { list ->
                    mListUpcoming.onNext(list)
                }
            }.addToDisposable()

        //GetMoviePopular
        Observable.merge(refresh, loadMore)
            .switchMap {
                doGetMoviePopular(AppConstants.API_KEY, page)
            }.subscribe {
                it.results.let { list ->
                    mListMoviePopular.onNext(list)
                }
            }.addToDisposable()

        //GetMovieTopRated
        Observable.merge(refresh, loadMore)
            .switchMap {
                doGetMovieTopRated(AppConstants.API_KEY, page)
            }.subscribe {
                it.results.let { list ->
                    mListMovieTopRated.onNext(list)
                }
            }.addToDisposable()*/




        //
        doGetMovieNowPlaying(AppConstants.API_KEY).subscribe({ result ->
            result.results.let { list ->
                mListNowPlaying.onNext(list)
            }
        }, { error ->
            Log.d("TAG", "transform: $error")
        }).addToDisposable()

        doGetMovieUpcoming(AppConstants.API_KEY).subscribe({ result ->
            result.results.let { list ->
                mListUpcoming.onNext(list)
            }
        }, { error ->
            Log.d("TAG", "transform: $error")
        }).addToDisposable()


        doGetMoviePopular(AppConstants.API_KEY).subscribe({ result ->
            result.results.let { list ->
                mListMoviePopular.onNext(list)
            }
        }, { error ->
            Log.d("TAG", "transform: $error")
        }).addToDisposable()


        doGetMovieTopRated(AppConstants.API_KEY).subscribe({ result ->
            result.results.let {
                mListMovieTopRated.onNext(it)
            }
        }, { error ->
            Log.d("TAG", "transform: $error")
        }).addToDisposable()



        return Output(mListNowPlaying, mListUpcoming, mListMoviePopular, mListMovieTopRated)
    }

    private fun doGetMovieNowPlaying(apiKey: String): Observable<MovieNowPlaying> {
        return mDataManager.doGetMovieNowPlaying(MovieNowPlayingRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun doGetMovieUpcoming(apiKey: String): Observable<MovieUpcoming> {
        return mDataManager.doGetMovieUpcoming(MovieUpcomingRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun doGetMoviePopular(apiKey: String): Observable<MoviePopular> {
        return mDataManager.doGetMoviePopular(MoviePopularRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun doGetMovieTopRated(apiKey: String): Observable<MovieTopRated> {
        return mDataManager.doGetMovieTopRated(MovieTopRatedRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }


}