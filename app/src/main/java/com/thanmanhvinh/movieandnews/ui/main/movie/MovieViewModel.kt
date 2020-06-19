package com.thanmanhvinh.movieandnews.ui.main.movie

import android.util.Log
import com.thanmanhvinh.movieandnews.data.api.*
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class MovieViewModel : BaseViewModel<Any, MovieViewModel.Output>() {
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

        doGetMovieNowPlaying().subscribe({ result ->
            result.results.let {list ->
                mListNowPlaying.onNext(list)
            }
        }, { error ->
            Log.d("TAG", "transform: $error")
        }).addToDisposable()


        doGetMovieUpcoming().subscribe({ result ->
            result.results.let { list ->
                mListUpcoming.onNext(list)
            }
        }, { error ->
            Log.d("TAG", "transform: $error")
        }).addToDisposable()


        doGetMoviePopular().subscribe({ result ->
            result.results.let { list ->
                mListMoviePopular.onNext(list)
            }
        }, { error ->
            Log.d("TAG", "transform: $error")
        }).addToDisposable()


        doGetMovieTopRated().subscribe({ result ->
            result.results.let {
                mListMovieTopRated.onNext(it)
            }
        }, { error ->
            Log.d("TAG", "transform: $error")
        }).addToDisposable()



        return Output(mListNowPlaying, mListUpcoming, mListMoviePopular, mListMovieTopRated)
    }

    private fun doGetMovieNowPlaying(): Observable<MovieNowPlaying> {
        return mDataManager.doGetMovieNowPlaying()
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun doGetMovieUpcoming(): Observable<MovieUpcoming> {
        return mDataManager.doGetMovieUpcoming()
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun doGetMoviePopular(): Observable<MoviePopular> {
        return mDataManager.doGetMoviePopular()
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun doGetMovieTopRated(): Observable<MovieTopRated> {
        return mDataManager.doGetMovieTopRated()
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }


}