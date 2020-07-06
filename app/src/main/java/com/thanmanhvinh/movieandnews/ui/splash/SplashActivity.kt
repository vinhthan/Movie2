package com.thanmanhvinh.movieandnews.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.Token
import com.thanmanhvinh.movieandnews.ui.base.BaseActivity
import com.thanmanhvinh.movieandnews.ui.main.MainActivity
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.TimeUnit

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
                        tvToken.text = tv
                    }
                }.addToDisosable()
        }



        val intent = Intent(this, MainActivity::class.java)
        val bundle = Bundle()
            bundle.putString(AppConstants.TOKEN, tvToken.toString())
            startActivity(intent, bundle)
        finish()

    }

}