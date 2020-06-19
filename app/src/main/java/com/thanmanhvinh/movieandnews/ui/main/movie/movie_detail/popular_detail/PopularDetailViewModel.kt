package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.popular_detail

import android.util.Log
import com.thanmanhvinh.movieandnews.data.api.MoviePopular
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*

class PopularDetailViewModel: BaseViewModel<Any, PopularDetailViewModel.Output>() {
    data class Output (
        val detailPopular: Observable<MoviePopular.Result>
    )


    override fun transform(input: Any): Output {
        val mDetailPopular = BehaviorSubject.create<MoviePopular.Result>()

        doGetDetailPopular().subscribe({result ->
            if (result.results.size > 0) {
                mDetailPopular.onNext(result.results[0])
            }

        },{error ->
            Log.d("TAG", "transform: $error")
        }).addToDisposable()

        return Output(mDetailPopular)
    }

    private fun doGetDetailPopular(): Observable<MoviePopular>{
        return mDataManager.doGetMoviePopular()
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }
}