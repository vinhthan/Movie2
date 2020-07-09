package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.upcoming_detail

import android.util.Log
import com.thanmanhvinh.movieandnews.data.api.*
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class UpcomingDetailViewModel :
    BaseViewModel<UpcomingDetailViewModel.Input, UpcomingDetailViewModel.Output>() {
    data class Input(
        val id: Observable<Int>
    )

    data class Output(
        val detail: Observable<MovieDetail>,
        val listGenres: Observable<MutableList<MovieDetail.Genre>>,
        val listCountries: Observable<MutableList<MovieDetail.ProductionCountry>>,
        val listReview: Observable<MutableList<MovieReview.Result>>,
        val listSimilar: Observable<MutableList<MovieSimilar.Result>>,
        val errorToast: Observable<String>
    )

    override fun transform(input: Input): Output {
        val mDetail = BehaviorSubject.create<MovieDetail>()
        val mListGenres = BehaviorSubject.create<MutableList<MovieDetail.Genre>>()
        val mListCountries = BehaviorSubject.create<MutableList<MovieDetail.ProductionCountry>>()
        val mListReview = BehaviorSubject.create<MutableList<MovieReview.Result>>()
        val mListSimilar = BehaviorSubject.create<MutableList<MovieSimilar.Result>>()
        val mErrorToast = BehaviorSubject.create<String>()

        input.id.flatMap { doGetDetail(it, AppConstants.API_KEY) }
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
            .subscribe({detail ->
                mDetail.onNext(detail)

                detail.genres.let { list ->
                    mListGenres.onNext(list as MutableList<MovieDetail.Genre>)
                }

                detail.productionCountries.let { list ->
                    mListCountries.onNext(list as MutableList<MovieDetail.ProductionCountry>)
                }

            }, {
                mErrorToast.onNext(it.toString())
            }).addToDisposable()

        input.id.flatMap { doGetReview(it, AppConstants.API_KEY) }
            .subscribe({review ->
                review.results.let { list ->
                    mListReview.onNext(list as MutableList<MovieReview.Result>)
                }
            },{
                mErrorToast.onNext(it.toString())
            }).addToDisposable()

        input.id.flatMap { doGetSimilar(it, AppConstants.API_KEY) }
            .subscribe({similar ->
                similar.results.let { list ->
                    mListSimilar.onNext(list as MutableList<MovieSimilar.Result>)
                }
            }, {
                mErrorToast.onNext(it.toString())
            }).addToDisposable()

        return Output(mDetail, mListGenres, mListCountries, mListReview, mListSimilar, mErrorToast)
    }

    private fun doGetDetail(id: Int, apiKey: String): Observable<MovieDetail>{
        return mDataManager.doGetMovieDetail(id, MovieDetailRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun doGetReview(id: Int, apiKey: String): Observable<MovieReview>{
        return mDataManager.doGetReview(id, MovieReviewRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun doGetSimilar(id: Int, apiKey: String): Observable<MovieSimilar>{
        return mDataManager.doGetSimilar(id, MovieSimilarRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

}