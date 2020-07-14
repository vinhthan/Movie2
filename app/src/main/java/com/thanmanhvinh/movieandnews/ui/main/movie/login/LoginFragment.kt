package com.thanmanhvinh.movieandnews.ui.main.movie.login

import android.content.Intent
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.jakewharton.rxbinding3.widget.textChanges
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import com.thanmanhvinh.movieandnews.utils.exception.AuthenticateException
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : BaseFragment<LoginViewModel>() {
    var callbackManager: CallbackManager = CallbackManager.Factory.create()

    private var token = BehaviorSubject.create<String>()
    private var triggerLogin = PublishSubject.create<Unit>()

    override fun createViewModel(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_login

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

        val bundle = arguments?.getString(AppConstants.TOKEN)
        bundle?.let {
            token.onNext(it)
        }

        val output = mViewModel.transform(
            LoginViewModel.Input(
                token,
                edtUsername.textChanges().map { it.toString() },
                edtPassword.textChanges().map { it.toString() },
                triggerLogin = triggerLogin
            )
        )

        btnLogin.setOnClickListener {
            triggerLogin.onNext(Unit)
            //Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
        }

        with(output) {
            login.observeOn(schedulerProvider.ui)
                .subscribe {
                    findNavController().navigate(R.id.movieFragment)
                    Toast.makeText(context, R.string.login_success, Toast.LENGTH_SHORT).show()
                }
            error.observeOn(schedulerProvider.ui)
                .subscribe {error ->
                    when(error){
                        AuthenticateException.EMPTY_USERNAME, AuthenticateException.USERNAME_TOO_SHORT -> {
                            edtUsername.error = getString(error.message)
                        }
                        AuthenticateException.EMPTY_PASSWORD, AuthenticateException.PASSWORD_TOO_SHORT -> {
                            edtPassword.error = getString(error.message)
                        }
                        else -> null
                    }
                }
            errorToast.observeOn(schedulerProvider.ui)
                .subscribe {
                    Toast.makeText(context, R.string.wrong_username_password, Toast.LENGTH_SHORT).show()
                    //Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                }
        }.addToDisposable()

    }

    override fun initData() {
        imgBackLogin.setOnClickListener {
            onButtonBackClick()
        }

        /**
         * Facebook
         */
        login_button.fragment = this

        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                // App code
                findNavController().navigate(R.id.movieFragment)
            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })

        login_button.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile"))

        }


    }

    override fun onResume() {
        checkLogin()
        super.onResume()
    }

    private fun checkLogin() {
        if (mViewModel.isUnauthorized()) {
            btnLogin.isEnabled = false
        } else {
            btnLogin.isEnabled = true
        }


    }


    /**
     * result login Facebook
     */
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}