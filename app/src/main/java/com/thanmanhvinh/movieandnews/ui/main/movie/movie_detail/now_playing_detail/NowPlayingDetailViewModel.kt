package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.now_playing_detail

import android.util.Log
import com.thanmanhvinh.movieandnews.data.api.*
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
        val listGenres: Observable<MutableList<MovieDetail.Genre>>,
        val listCountries: Observable<MutableList<MovieDetail.ProductionCountry>>,
        val listReview: Observable<MutableList<MovieReview.Result>>
    )

    override fun transform(input: Input): Output {
        val mDetail = BehaviorSubject.create<MovieDetail>()
        val mListGenres = BehaviorSubject.create<MutableList<MovieDetail.Genre>>()
        val mListCountries = BehaviorSubject.create<MutableList<MovieDetail.ProductionCountry>>()
        val mListReview = BehaviorSubject.create<MutableList<MovieReview.Result>>()

        input.id.flatMap { doGetDetail(it, AppConstants.API_KEY) }
            .subscribe({detail ->
                mDetail.onNext(detail)

                detail.genres.let {listGenres ->
                    mListGenres.onNext(listGenres as MutableList<MovieDetail.Genre>)
                }

                detail.productionCountries.let { listCountries ->
                    mListCountries.onNext(listCountries as MutableList<MovieDetail.ProductionCountry>)
                }

            }, {
                //Log.d("TAG", "error $it")
            }).addToDisposable()

        input.id.flatMap { doGetReview(it, AppConstants.API_KEY) }
            .subscribe({review ->
                review.results.let { list ->
                    mListReview.onNext(list as MutableList<MovieReview.Result>)
                }
            },{
                //Log.d("TAG", "error $it")
            }).addToDisposable()

/*        input.id.flatMap { doGetMovieVideo(it, AppConstants.API_KEY) }
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
            .subscribe({video ->
                mVideo.onNext(video as MutableList<MovieVideo.Result>)
            },{
                Log.d("TAG", "error $it")
            }).addToDisposable()*/


        /* mDetail.flatMap { movie ->
             doGetDetail(movie.id, AppConstants.API_KEY)
         }.subscribe({detail ->
             detail.let {
                 mDetail.onNext(it)
             }
         },{error ->
             Log.d("TAG", "error $error")
         }).addToDisposable()*/

        return Output(mDetail, mListGenres, mListCountries, mListReview)
    }

    private fun doGetDetail(id: Int, apiKey: String): Observable<MovieDetail> {
        return mDataManager.doGetMovieDetail(id, MovieDetailRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun doGetReview(id: Int, apiKey: String): Observable<MovieReview>{
        return mDataManager.doGetReview(id, MovieReviewRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

}