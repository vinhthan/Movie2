package com.thanmanhvinh.movieandnews.ui.main.movie

import android.util.Log
import com.thanmanhvinh.movieandnews.data.api.*
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class MovieViewModel : BaseViewModel<MovieViewModel.Input, MovieViewModel.Output>() {

/*    private var page = AppConstants.LIST_PAGE
    private var limit = AppConstants.LIST_LIMIT

    data class Input (
        val triggerLoadMore: Observable<Boolean>,
        val triggerRefresh: Observable<Boolean>
    )*/

    data class Input (
        val triggerLogout: Observable<Unit>
    )

    data class Output(
        val isLogout: Observable<Boolean>,
        val listNowPlaying: Observable<MutableList<MovieNowPlaying.Results>>,
        val listUpcoming: Observable<MutableList<MovieUpcoming.Result>>,
        val listPopular: Observable<MutableList<MoviePopular.Result>>,
        val listTopRated: Observable<MutableList<MovieTopRated.Results>>,
        val token: Observable<Token>,
        val errorToast: Observable<String>
    )




    override fun transform(input: Input): Output {
        val mIsLogout = PublishSubject.create<Boolean>()
        val mListNowPlaying = BehaviorSubject.create<MutableList<MovieNowPlaying.Results>>()
        val mListUpcoming = BehaviorSubject.create<MutableList<MovieUpcoming.Result>>()
        val mListMoviePopular = BehaviorSubject.create<MutableList<MoviePopular.Result>>()
        val mListMovieTopRated = BehaviorSubject.create<MutableList<MovieTopRated.Results>>()
        val mToken = BehaviorSubject.create<Token>()
        val mErrorToast = BehaviorSubject.create<String>()

        with(input){
            triggerLogout.observeOn(mSchedulerProvider.ui)
                .subscribe({
                    if (mDataManager.checkLogin()){
                        mIsLogout.onNext(true)
                    }else{
                        mIsLogout.onNext(false)
                    }
                },{

                })
        }.addToDisposable()

        //
        doGetMovieNowPlaying(AppConstants.API_KEY).subscribe({ result ->
            result.results.let { list ->
                mListNowPlaying.onNext(list)
            }
        }, { error ->
            mErrorToast.onNext(error.toString())
        }).addToDisposable()

        doGetMovieUpcoming(AppConstants.API_KEY).subscribe({ result ->
            result.results.let { list ->
                mListUpcoming.onNext(list)
            }
        }, { error ->
            mErrorToast.onNext(error.toString())
        }).addToDisposable()


        doGetMoviePopular(AppConstants.API_KEY).subscribe({ result ->
            result.results.let { list ->
                mListMoviePopular.onNext(list)
            }
        }, { error ->
            mErrorToast.onNext(error.toString())
        }).addToDisposable()


        doGetMovieTopRated(AppConstants.API_KEY).subscribe({ result ->
            result.results.let {
                mListMovieTopRated.onNext(it)
            }
        }, { error ->
            mErrorToast.onNext(error.toString())
        }).addToDisposable()

        doGetToken(AppConstants.API_KEY)
            .subscribe({token ->
                mToken.onNext(token)
            },{error ->
                mErrorToast.onNext(error.toString())
            }).addToDisposable()



        return Output(mIsLogout, mListNowPlaying, mListUpcoming, mListMoviePopular, mListMovieTopRated, mToken, mErrorToast)
    }

    private fun doGetMovieNowPlaying(apiKey: String): Observable<MovieNowPlaying> {
        return mDataManager.doGetMovieNowPlaying(MovieNowPlayingRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun doGetMovieUpcoming(apiKey: String): Observable<MovieUpcoming> {
        return mDataManager.doGetMovieUpcoming(MovieUpcomingRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun doGetMoviePopular(apiKey: String): Observable<MoviePopular> {
        return mDataManager.doGetMoviePopular(MoviePopularRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun doGetMovieTopRated(apiKey: String): Observable<MovieTopRated> {
        return mDataManager.doGetMovieTopRated(MovieTopRatedRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun doGetToken(apiKey: String): Observable<Token> {
        return mDataManager.doGetToken(TokenRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    /**
     * logout
     */
    fun triggerLogout(){
        mDataManager.doLogout(LogoutRequest(AppConstants.API_KEY, AppConstants.SESSION_ID))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
            .subscribe ({
                mDataManager.logout()
            }, {

            }).addToDisposable()
    }

/*    fun doLogout(apiKey: String, sessionId: String){
        mDataManager.doLogout(LogoutRequest(apiKey, sessionId))
    }*/







}