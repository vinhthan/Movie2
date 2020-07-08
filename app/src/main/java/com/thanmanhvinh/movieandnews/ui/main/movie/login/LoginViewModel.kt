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

class LoginViewModel : BaseViewModel<LoginViewModel.Input, LoginViewModel.Output>() {
    lateinit var context: Context

    data class Input(
        val token: Observable<String>,
        val username: Observable<String>,
        val password: Observable<String>,
        val triggerLogin: Observable<Unit>
    )

    data class Output(
        val login: Observable<Login>
    )

    override fun transform(input: Input): Output {
        val mUsername: BehaviorSubject<String> = BehaviorSubject.create()
        val mPassword: BehaviorSubject<String> = BehaviorSubject.create()
        val mLogin = BehaviorSubject.create<Login>()
        //val mError = BehaviorSubject.create<String>()


        with(input) {
            username.subscribe(mUsername)
            password.subscribe(mPassword)
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


            token.flatMap { tok ->
                triggerLogin.flatMap {
                    val usernameStr = mUsername.value ?: ""
                    val passwordStr = mPassword.value ?: ""
                    doLogin(AppConstants.API_KEY, usernameStr, passwordStr, tok)
                }
            }.subscribe({ login ->
                    mLogin.onNext(login)
                }, { error ->
                    Log.d("TAG", "error $error")
                }).addToDisposable()

            //
/*            token.flatMap { tok ->
                    doLogin(AppConstants.API_KEY, username.toString(), password.toString(), tok)

            }.subscribe({ login ->
                triggerLogin.subscribe {
                    val usernameStr = mUsername.value ?: ""
                    val passwordStr = mPassword.value ?: ""
                    doLogin(AppConstants.API_KEY, usernameStr, passwordStr, token.toString())
                        .subscribe({
                            mLogin.onNext(login)
                        },{error ->
                            Log.d("TAG", "error $error")

                        }).addToDisposable()
                }

            }, { error ->
                Log.d("TAG", "error $error")
            }).addToDisposable()*/


/*            token.flatMap {
                doLogin(AppConstants.API_KEY, usernameStr, passwordStr, it)
            }.subscribe({ login ->
                mLogin.onNext(login)
            }, { error ->
                Log.d("TAG", "error $error")
            })*/

        }


        return Output(mLogin)
    }

    private fun doLogin(
        apiKey: String,
        username: String,
        password: String,
        requestToken: String
    ): Observable<Login> {
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