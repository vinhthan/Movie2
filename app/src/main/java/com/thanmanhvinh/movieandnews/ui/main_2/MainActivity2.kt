package com.thanmanhvinh.movieandnews.ui.main_2

import android.content.Intent
import android.os.Bundle
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseActivity
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.base.FragmentBackStackManager
import com.thanmanhvinh.movieandnews.ui.base.INavigatorActivity
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import kotlin.reflect.KClass

class MainActivity2 : BaseActivity<MainViewModel2>(), INavigatorActivity {

    override lateinit var currentFragment: BaseFragment<*>
    private lateinit var mFragmentBackStackManager: FragmentBackStackManager

    override fun createViewModel(): Class<MainViewModel2> = MainViewModel2::class.java

    override fun getContentView(): Int = R.layout.activity_main2

    override fun initView() {
        mFragmentBackStackManager = FragmentBackStackManager(supportFragmentManager)
        //setSupportActionBar(toolBar)

        val bundle = intent.getBundleExtra(AppConstants.HOME)
    }

    override fun bindViewModel() {

    }

    override fun onFragmentResumed(fragment: BaseFragment<*>) {
        currentFragment = fragment
    }

    override fun switchFragment(fragment: KClass<*>, addToBackStack: Boolean, bundle: Bundle?) {
        mFragmentBackStackManager.switchFragment(
            fragment, addToBackStack, bundle
        )
    }

    override fun setAppBarTitle(title: String) {

    }

    override fun onBackPressed(bundle: Bundle?) {
        super.onBackPressed()
    }

    override fun showActivity(activity: Class<*>, key: String?, bundle: Bundle?) {
        val intent = Intent(this, activity)
        if (key != null && bundle != null) {
            intent.putExtra(key, bundle)
        }
        startActivity(intent)
    }





}