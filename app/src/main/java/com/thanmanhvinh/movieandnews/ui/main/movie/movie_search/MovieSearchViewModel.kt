package com.thanmanhvinh.movieandnews.ui.main.movie.movie_search

import com.thanmanhvinh.movieandnews.data.api.MovieSearch
import com.thanmanhvinh.movieandnews.data.api.MovieSearchRequest
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class MovieSearchViewModel :
    BaseViewModel<MovieSearchViewModel.Input, MovieSearchViewModel.Output>() {

    data class Input(
        val edtSearch: Observable<String>,
        val triggerSearch: Observable<Unit>
    )

    data class Output(
        val loadList: Observable<MutableList<MovieSearch.Result>>,
        val errorToast: Observable<String>
    )

    override fun transform(input: Input): Output {
        val mEdtSearch: BehaviorSubject<String> = BehaviorSubject.create()
        val mLoadList = BehaviorSubject.create<MutableList<MovieSearch.Result>>()
        val mErrorToast = BehaviorSubject.create<String>()

        try {
            with(input) {
                edtSearch.subscribe(mEdtSearch)
                triggerSearch.subscribe {
                    val edtSearchStr = mEdtSearch.value ?: ""
                    doGetMovieSearch(AppConstants.API_KEY, edtSearchStr)
                        .subscribe({ movie ->
                            movie.results.let { list ->
                                mLoadList.onNext(list as MutableList<MovieSearch.Result>)
                            }
                        }, {

                        })
                }.addToDisposable()

            }
        }catch (e: Exception){
            /*for (error in e.getErrors()) {
                if (error is RateExceededError) {
                    val rateExceeded: RateExceededError = error as RateExceededError
                    Thread.sleep(rateExceeded.getRetryAfterSeconds() * 1000)
                }
            }*/
        }



/*        doGetMovieSearch(AppConstants.API_KEY, mEdtSearch.toString())
            .subscribe({movie ->
                movie.results.let { list ->
                    mLoadList.onNext(list as MutableList<MovieSearch.Result>)
                }
            }, {
                Log.d("TAG", "error $it")
            }).addToDisposable()*/



        return Output(mLoadList, mErrorToast)
    }

    private fun doGetMovieSearch(apiKey: String, query: String): Observable<MovieSearch> {
        return mDataManager.doGetMovieSearch(MovieSearchRequest(apiKey, query))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }
}

//khi ma k co du lieu thi se ngung call api ngay luc do de xly ng dung nhap qua nhieu ky tu vao o tim kiem
//collapse se bo viewholder... de co the xly dc bam 2 lan ms nhan
