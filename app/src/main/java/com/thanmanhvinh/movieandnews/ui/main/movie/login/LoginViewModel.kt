package com.thanmanhvinh.movieandnews.ui.main.movie.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.Login
import com.thanmanhvinh.movieandnews.data.api.LoginRequest
import com.thanmanhvinh.movieandnews.data.api.Token
import com.thanmanhvinh.movieandnews.data.api.TokenRequest
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class LoginViewModel: BaseViewModel<LoginViewModel.Input, LoginViewModel.Output>() {
    lateinit var context: Context
    lateinit var tok: String
    data class Input(
        val username: Observable<String>,
        val password: Observable<String>,
        val triggerLogin: Observable<Unit>
    )

    data class Output (
        val token: Observable<Token>,
        val login: Observable<Boolean>
    )

    override fun transform(input: Input): Output {
        val mUsername: BehaviorSubject<String> = BehaviorSubject.create()
        val mPassword: BehaviorSubject<String> = BehaviorSubject.create()
        val mToken = BehaviorSubject.create<Token>()
        val mLogin = BehaviorSubject.create<Boolean>()

        with(input){
            username.subscribe(mUsername)
            password.subscribe(mPassword)
            triggerLogin.map {
                val usernameStr = mUsername.value ?: ""
                val passwordStr = mPassword.value ?: ""
                val valid = validate(usernameStr, passwordStr)
                if (valid != null){
                    Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show()
                }else if(valid == null){
                    doGetToken(AppConstants.API_KEY)
                        .subscribe({token ->
                            token.requestToken.let { tok ->
                                mToken.onNext(tok as Token)
                                this@LoginViewModel.tok = mToken.toString()
                            }
                        },{error ->
                            Log.d("TAG", "error $error")
                        }).addToDisposable()
                    doLogin(AppConstants.API_KEY, usernameStr, passwordStr, tok)
                        .subscribe({
                            mLogin.onNext(true)
                        },{error ->
                            Log.d("TAG", "error $error")
                        }).addToDisposable()
                }
            }

        }


        return Output(mToken, mLogin)
    }

    private fun doGetToken(apiKey: String): Observable<Token>{
        return mDataManager.doGetToken(TokenRequest(apiKey))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun doLogin(apiKey: String, username: String, password: String, requestToken: String): Observable<Login>{
        return mDataManager.doLogin(LoginRequest(apiKey, username, password, requestToken))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun validate(
        username: String,
        password: String
    ): Int {
        when {
            username.isEmpty() -> {
                return R.string.empty_username
            }
            password.isEmpty() -> {
                return R.string.empty_password
            }
            else -> return R.string.empty
        }


    }

}