package com.thanmanhvinh.movieandnews.ui.main.movie.login

import android.content.Context
import android.util.Log
import com.androidnetworking.error.ANError
import com.thanmanhvinh.movieandnews.data.api.Login
import com.thanmanhvinh.movieandnews.data.api.LoginRequest
import com.thanmanhvinh.movieandnews.ui.base.BaseViewModel
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import com.thanmanhvinh.movieandnews.utils.exception.AuthenticateException
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class LoginViewModel : BaseViewModel<LoginViewModel.Input, LoginViewModel.Output>() {
    lateinit var context: Context

    data class Input(
        val token: Observable<String>,
        val username: Observable<String>,
        val password: Observable<String>,
        val triggerLogin: Observable<Unit>
    )

    data class Output(
        val login: Observable<Login>,
        val error: Observable<AuthenticateException>,
        val errorToast: Observable<String>
    )

    override fun transform(input: Input): Output {
        val mUsername: BehaviorSubject<String> = BehaviorSubject.create()
        val mPassword: BehaviorSubject<String> = BehaviorSubject.create()
        val mLogin = BehaviorSubject.create<Login>()
        val mError = BehaviorSubject.create<AuthenticateException>()
        val mErrorToast = BehaviorSubject.create<String>()


        with(input) {
            username.subscribe(mUsername)
            password.subscribe(mPassword)
            //
            token.map { tok ->
                toks(tok)
            }.subscribe {
                triggerLogin.subscribe {
                    val usernameStr = mUsername.value ?: ""
                    val passwordStr = mPassword.value ?: ""
                    val valid = validate(usernameStr, passwordStr)
                    if (valid == null) {
                        doLogin(AppConstants.API_KEY, usernameStr, passwordStr, toks(tok = String()))
                            .subscribe({ login ->
                                mLogin.onNext(login)
                                mDataManager.saveAccount(usernameStr, passwordStr, toks(tok = String()))
                            }, {error ->
                                //mErrorToast.onNext(error.toString())
                                error.let {
                                    if (error is ANError){
                                        if (error.errorCode == 401){
                                            mErrorToast.onNext(error.toString())
                                        }else if (error.errorCode == 404){
                                            mErrorToast.onNext(error.toString())
                                        }
                                    }
                                }
                            })
                    } else {
                        mError.onNext(valid)
                    }

                }
            }.addToDisposable()


/*            triggerLogin.subscribe {
                val usernameStr = mUsername.value ?: ""
                val passwordStr = mPassword.value ?: ""

                doLogin(AppConstants.API_KEY, usernameStr, passwordStr, it.toString())
                    .subscribe({ login ->
                        mLogin.onNext(login as Login)
                    }, { error ->
                        Log.d("TAG", "error $error")
                    })
            }*/

/*            token.flatMap { tok ->
                triggerLogin.flatMap {
                    val usernameStr = mUsername.value ?: ""
                    val passwordStr = mPassword.value ?: ""
                    doLogin(AppConstants.API_KEY, usernameStr, passwordStr, tok)
                }
            }.subscribe({ login ->
                    mLogin.onNext(login)
                }, { error ->
                    Log.d("TAG", "error $error")
                }).addToDisposable()*/

        }


        return Output(mLogin, mError, mErrorToast)
    }

    private fun doLogin(apiKey: String, username: String, password: String, requestToken: String): Observable<Login> {
        return mDataManager.doLogin(LoginRequest(apiKey, username, password, requestToken))
            .subscribeOn(mSchedulerProvider.io)
            .observeOn(mSchedulerProvider.ui)
    }

    private fun toks(tok: String): String {
        return tok
    }

    private fun validate(username: String, password: String): AuthenticateException? {
        return when {
            username.isEmpty() -> {
                return AuthenticateException.EMPTY_USERNAME
            }
            username.length < 8 -> {
                return AuthenticateException.USERNAME_TOO_SHORT
            }
            password.isEmpty() -> {
                return AuthenticateException.EMPTY_PASSWORD
            }
            password.length < 5 || password.length > 16 -> {
                return AuthenticateException.PASSWORD_TOO_SHORT
            }
            else -> null
        }
    }


    fun isUnauthorized(): Boolean {
        return mDataManager.checkLogin()
    }


}