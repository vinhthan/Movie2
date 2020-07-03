package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.top_rated_detail

import android.util.Log
import com.thanmanhvinh.movieandnews.data.api.*
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class TopRatedDetailViewModel: BaseViewModel<TopRatedDetailViewModel.Input, TopRatedDetailViewModel.Output>() {
    data class Input (
        val id: Observable<Int>
    )

    data class Output (
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

                detail.genres.let { list ->
                    mListGenres.onNext(list as MutableList<MovieDetail.Genre>)
                }

                detail.productionCountries.let { list ->
                    mListCountries.onNext(list as MutableList<MovieDetail.ProductionCountry>)
                }

            },{
                //Log.d("TAG", "reeor $it")
            }).addToDisposable()

        input.id.flatMap { doGetReview(it, AppConstants.API_KEY) }
            .subscribe({review ->
                review.results.let { list ->
                    mListReview.onNext(list as MutableList<MovieReview.Result>)
                }
            },{
                //Log.d("TAG", "error $it")
            }).addToDisposable()


        return Output(mDetail, mListGenres, mListCountries, mListReview)
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


}