package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.now_playing_detail

import android.util.Log
import com.androidnetworking.error.ANError
import com.thanmanhvinh.movieandnews.data.api.MovieNowPlaying
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class NowPlayingDetailViewModel : BaseViewModel<Any, Any>() {
    override fun transform(input: Any): Any {
        TODO("Not yet implemented")
    }


/*    data class Output(
        val detail: BehaviorSubject<MovieNowPlaying.Results>
    )

*//*    data class Input (
        val position: Observable<MutableList<MovieNowPlaying.Results>>
    )*//*


    override fun transform(input: Any): Output {
        val mDetail = BehaviorSubject.create<MovieNowPlaying.Results>()


        doGetDetailNowPlaying().subscribe({result ->

            if (result.results.size > 0){
                mDetail.onNext(result.results[0])
            }
        }, { error ->
            Log.d("TAG", "transform: $error")
        }).addToDisposable()

        return Output(mDetail)
    }

    private fun doGetDetailNowPlaying(): Observable<MovieNowPlaying> {
        return mDataManager.doGetMovieNowPlaying()
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }*/

}