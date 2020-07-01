package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.video

import android.util.Log
import com.thanmanhvinh.movieandnews.data.api.MovieVideo
import com.thanmanhvinh.movieandnews.data.api.MovieVideoRequest
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class VideoViewModel: BaseViewModel<VideoViewModel.Input, VideoViewModel.Output>() {
    data class Input (
        val id: Observable<Int>
    )

    data class Output (
        val video: Observable<MutableList<MovieVideo.Result>>
    )

    override fun transform(input: Input): Output {
        val mVideo = BehaviorSubject.create<MutableList<MovieVideo.Result>>()

        input.id.flatMap { doGetMovieVideo(it, AppConstants.API_KEY) }
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
            .subscribe({video ->
                video.results.let { listVideo ->
                    mVideo.onNext(listVideo as MutableList<MovieVideo.Result>)
                }
            },{
                Log.d("TAG", "error $it")
            }).addToDisposable()


        return Output(mVideo)
    }

    private fun doGetMovieVideo(id: Int, apiKey: String): Observable<MovieVideo>{
        return mDataManager.doGetMovieVideo(id, MovieVideoRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }


}