package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.top_rated_detail

import android.util.Log
import com.thanmanhvinh.movieandnews.data.api.MovieTopRated
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class TopRatedDetailViewModel: BaseViewModel<Any, Any>() {
    override fun transform(input: Any): Any {
        TODO("Not yet implemented")
    }
/*    data class Output (
        val detailTopRated: Observable<MovieTopRated.Results>
    )

    override fun transform(input: Any): Output {
        val mDetaiTopRated = BehaviorSubject.create<MovieTopRated.Results>()

        doGetTopRatedDetail().subscribe({result ->
            *//*result.id.let { id ->
                mDetaiTopRated.onNext(id)
            }*//*
        },{error ->
            Log.d("TAG", "transform: $error")
        }).addToDisposable()

        return Output(mDetaiTopRated)
    }

    private fun doGetTopRatedDetail(): Observable<MovieTopRated.Results>{
        return mDataManager.doGetMovieTopRatedDetail()
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }*/


}