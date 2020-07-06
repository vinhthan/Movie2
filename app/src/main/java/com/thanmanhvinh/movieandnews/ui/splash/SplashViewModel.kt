package com.thanmanhvinh.movieandnews.ui.splash

import android.util.Log
import com.thanmanhvinh.movieandnews.data.api.Token
import com.thanmanhvinh.movieandnews.data.api.TokenRequest
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class SplashViewModel: BaseViewModel<Any, SplashViewModel.Output>() {

    data class Output (
        val token: Observable<Token>
        //val gotoLogin: Observable<Boolean>
    )

    override fun transform(input: Any): Output {
        val mToken = BehaviorSubject.create<Token>()

        doGetToken(AppConstants.API_KEY)
            .subscribe({
                it.requestToken.let { token ->
                    mToken.onNext(token as Token)
                }
            }, {error ->
                Log.d("TAG", "error $error")
            }).addToDisposable()


        return Output(mToken)
    }

    private fun doGetToken(apiKey: String): Observable<Token>{
        return mDataManager.doGetToken(TokenRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

}