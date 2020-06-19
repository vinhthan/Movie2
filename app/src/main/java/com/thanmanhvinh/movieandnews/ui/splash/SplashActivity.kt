package com.thanmanhvinh.movieandnews.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseActivity
import com.thanmanhvinh.movieandnews.ui.main.MainActivity

class SplashActivity : BaseActivity<SplashViewModel>() {
    override fun createViewModel(): Class<SplashViewModel> = SplashViewModel::class.java

    override fun getContentView(): Int = R.layout.activity_splash

    override fun initView() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun bindViewModel() {

    }

}