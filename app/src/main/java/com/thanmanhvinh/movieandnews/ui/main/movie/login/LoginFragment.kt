package com.thanmanhvinh.movieandnews.ui.main.movie.login

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.Token
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.concurrent.TimeUnit

class LoginFragment : BaseFragment<LoginViewModel>() {
    private var tokens = BehaviorSubject.create<String>()

    override fun createViewModel(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_login

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

        val bundle = arguments?.getString(AppConstants.TOKEN)
        bundle?.let {
            tokens.onNext(it)
        }


        val output = mViewModel.transform(
            LoginViewModel.Input(
                edtUsername.textChanges().map { it.toString() },
                edtPassword.textChanges().map { it.toString() },
                btnLogin.clicks().throttleFirst(300, TimeUnit.MILLISECONDS)
            )
        )

        with(output){
            login.observeOn(schedulerProvider.ui)
                .subscribe {
                    findNavController().navigate(R.id.movieFragment)
                    Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
                }
        }

        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.movieFragment)
            Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
        }

    }

    override fun initData() {
/*        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.movieFragment)
            Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
        }*/
    }


}