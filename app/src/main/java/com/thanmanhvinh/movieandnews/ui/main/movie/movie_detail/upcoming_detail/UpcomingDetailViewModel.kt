package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.upcoming_detail

import android.util.Log
import com.thanmanhvinh.movieandnews.data.api.MovieDetail
import com.thanmanhvinh.movieandnews.data.api.MovieDetailRequest
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
        val listCountries: Observable<MutableList<MovieDetail.ProductionCountry>>
    )

    override fun transform(input: Input): Output {
        val mDetail = BehaviorSubject.create<MovieDetail>()
        val mListGenres = BehaviorSubject.create<MutableList<MovieDetail.Genre>>()
        val mListCountries = BehaviorSubject.create<MutableList<MovieDetail.ProductionCountry>>()

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
                Log.d("TAG", "error $it")
            }).addToDisposable()

        return Output(mDetail, mListGenres, mListCountries)
    }

    private fun doGetDetail(id: Int, apiKey: String): Observable<MovieDetail>{
        return mDataManager.doGetMovieDetail(id, MovieDetailRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

}