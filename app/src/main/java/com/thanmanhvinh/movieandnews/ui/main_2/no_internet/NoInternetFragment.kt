package com.thanmanhvinh.movieandnews.ui.main_2.no_internet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_no_internet.*

class NoInternetFragment : BaseFragment<NoInternetViewModel>() {
    override fun createViewModel(): Class<NoInternetViewModel> = NoInternetViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_no_internet

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {
        btnRetry.setOnClickListener {
            mNavigatorActivity.showActivity(MainActivity::class.java)
            activity?.finish()
        }
    }

    override fun initData() {

    }


}