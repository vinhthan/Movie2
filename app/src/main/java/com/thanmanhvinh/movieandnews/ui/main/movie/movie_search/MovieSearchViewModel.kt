package com.thanmanhvinh.movieandnews.ui.main.movie.movie_search

import android.util.Log
import com.thanmanhvinh.movieandnews.data.api.MovieSearch
import com.thanmanhvinh.movieandnews.data.api.MovieSearchRequest
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class MovieSearchViewModel: BaseViewModel<MovieSearchViewModel.Input, MovieSearchViewModel.Output>() {

    data class Input (
        val edtSearch: Observable<String>,
        val triggerSearch: Observable<Unit>
    )

    data class Output (
        val loadList: Observable<MutableList<MovieSearch.Result>>
    )

    override fun transform(input: Input): Output {
        val mEdtSearch: BehaviorSubject<String> = BehaviorSubject.create()
        val mLoadList = BehaviorSubject.create<MutableList<MovieSearch.Result>>()
        val mGotoSearch = BehaviorSubject.create<Boolean>()

        with(input){
            edtSearch.subscribe(mEdtSearch)

            triggerSearch.subscribe {
                val edtSearchStr = mEdtSearch.value ?: ""
                doGetMovieSearch(AppConstants.API_KEY, edtSearchStr)
                    .subscribe({movie ->
                        movie.results.let { list ->
                            mLoadList.onNext(list as MutableList<MovieSearch.Result>)
                        }
                    }, {
                        Log.d("TAG", "error $it")
                    })
            }.addToDisposable()

            }

/*        doGetMovieSearch(AppConstants.API_KEY, mEdtSearch.toString())
            .subscribe({movie ->
                movie.results.let { list ->
                    mLoadList.onNext(list as MutableList<MovieSearch.Result>)
                }
            }, {
                Log.d("TAG", "error $it")
            }).addToDisposable()*/

        return Output(mLoadList)
    }

    private fun doGetMovieSearch(apiKey: String, query: String): Observable<MovieSearch>{
        return mDataManager.doGetMovieSearch(MovieSearchRequest(apiKey, query))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }
}