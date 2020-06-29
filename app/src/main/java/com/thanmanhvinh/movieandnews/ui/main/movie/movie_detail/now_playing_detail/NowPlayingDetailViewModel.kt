package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.now_playing_detail

import android.util.Log
import com.thanmanhvinh.movieandnews.data.api.MovieDetail
import com.thanmanhvinh.movieandnews.data.api.MovieDetailRequest
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class NowPlayingDetailViewModel : BaseViewModel<NowPlayingDetailViewModel.Input, NowPlayingDetailViewModel.Output>() {

    data class Input(
        val movieDetail: MovieDetail
    )

   data class Output (
        val detail: Observable<MovieDetail>
    )

    override fun transform(input: Input): Output {
        val mMovieDetail: BehaviorSubject<MovieDetail> = BehaviorSubject.createDefault(input.movieDetail)
        val movies = mMovieDetail.value



        val mDetail = BehaviorSubject.create<MovieDetail>()
        val mId = BehaviorSubject.create<MovieDetail>()
        //input.id.subscribe(mId)
        val idInt = mId.value ?: ""

        if (movies != null) {
            doGetDetail(movies, AppConstants.API_KEY).subscribe({ data ->
                mDetail.onNext(data)
            }, { error ->
                Log.d("TAG", "error: $error")
            }).addToDisposable()
        }


        return Output(mDetail)
    }

    private fun doGetDetail(id: MovieDetail, apiKey: String): Observable<MovieDetail>{
        return mDataManager.doGetMovieDetail(id.id, MovieDetailRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }


}