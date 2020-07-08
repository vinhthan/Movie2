package com.thanmanhvinh.movieandnews.ui.main.movie.login

import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.Token
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.include_toolbar.*
import java.util.concurrent.TimeUnit

class LoginFragment : BaseFragment<LoginViewModel>() {
    private var token = BehaviorSubject.create<String>()
    private var trigger = PublishSubject.create<Unit>()

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
                triggerLogin = trigger
            )
        )

        btnLogin.setOnClickListener {
            trigger.onNext(Unit)
            //Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
        }

        with(output) {
            login.observeOn(schedulerProvider.ui)
                .subscribe {
                    findNavController().navigate(R.id.movieFragment)
                    //Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
                }
        }.addToDisposable()

    }

    override fun initData() {
        imgBackLogin.setOnClickListener {
            onButtonBackClick()
        }
    }


}