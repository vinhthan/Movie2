package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.now_playing_detail

import android.util.Log
import com.thanmanhvinh.movieandnews.data.api.MovieDetail
import com.thanmanhvinh.movieandnews.data.api.MovieDetailRequest
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class NowPlayingDetailViewModel :
    BaseViewModel<NowPlayingDetailViewModel.Input, NowPlayingDetailViewModel.Output>() {

    data class Input(
        val id: Observable<Int>
    )

    data class Output(
        val detail: Observable<MovieDetail>,
        val listGenres: BehaviorSubject<MutableList<MovieDetail.Genre>>
    )

    override fun transform(input: Input): Output {
        val mDetail = BehaviorSubject.create<MovieDetail>()
        val mListGenres = BehaviorSubject.create<MutableList<MovieDetail.Genre>>()

        input.id.flatMap { doGetDetail(it, AppConstants.API_KEY) }
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
            .subscribe({
                mDetail.onNext(it)
                it.genres.let {listGenres ->
                    mListGenres.onNext(listGenres as MutableList<MovieDetail.Genre>)
                }
            }, {
                Log.d("TAG", "error $it")
            }).addToDisposable()



        /* mDetail.flatMap { movie ->
             doGetDetail(movie.id, AppConstants.API_KEY)
         }.subscribe({detail ->
             detail.let {
                 mDetail.onNext(it)
             }
         },{error ->
             Log.d("TAG", "error $error")
         }).addToDisposable()*/

        return Output(mDetail, mListGenres)
    }

    private fun doGetDetail(id: Int, apiKey: String): Observable<MovieDetail> {
        return mDataManager.doGetMovieDetail(id, MovieDetailRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

}