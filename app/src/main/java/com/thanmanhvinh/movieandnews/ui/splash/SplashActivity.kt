package com.thanmanhvinh.movieandnews.ui.splash

import android.content.Intent
import android.os.Bundle
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseActivity
import com.thanmanhvinh.movieandnews.ui.main.MainActivity
import com.thanmanhvinh.movieandnews.ui.main_2.MainActivity2
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity<SplashViewModel>() {

    private var tok = BehaviorSubject.create<String>()
    override fun createViewModel(): Class<SplashViewModel> = SplashViewModel::class.java

    override fun getContentView(): Int = R.layout.activity_splash

    override fun initView() {

    }

    override fun bindViewModel() {


        val output = viewModel.transform(
            Any()
        )

        with(output){
            token.observeOn(schedulerProvider.ui)
                .subscribe { it ->
                    it.requestToken.let {
                        var tv = it
                    }
                }.addToDisosable()
        }



        val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        finish()

    }

}