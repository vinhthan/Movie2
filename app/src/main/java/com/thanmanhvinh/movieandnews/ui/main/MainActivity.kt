package com.thanmanhvinh.movieandnews.ui.main

import android.content.Intent
import android.os.Bundle
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseActivity
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.base.FragmentBackStackManager
import com.thanmanhvinh.movieandnews.ui.base.INavigatorActivity
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import kotlinx.android.synthetic.main.include_toolbar.*
import kotlin.reflect.KClass

class MainActivity : BaseActivity<MainViewModel>(), INavigatorActivity {

    override lateinit var currentFragment: BaseFragment<*>
    private lateinit var mFragmentBackStackManager: FragmentBackStackManager

    override fun createViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }


    override fun initView() {
        mFragmentBackStackManager = FragmentBackStackManager(supportFragmentManager)
        setSupportActionBar(toolBar)

        val bundle = intent.getBundleExtra(AppConstants.HOME)
/*        when{
            bundle != null -> switchFragment(MovieFragment::class, false)
        }*/
        //switchFragment(MovieFragment::class, false)

        //currentFragment.onButtonBackClick()


    }


    override fun bindViewModel() {


    }


    override fun onFragmentResumed(fragment: BaseFragment<*>) {
        currentFragment = fragment
        //toolBar.visibility = if (fragment.showToolBar) View.VISIBLE else View.GONE
        //tvTitleToolbar.text = getString(fragment.getTitleActionBar())

/*        toolBar.visibility = if (fragment.showToolBar) View.VISIBLE else View.GONE
        tvTitleToolbar.text = getString(fragment.getTitleActionBar())*/
        /*if(fragment.isRegisterFragment){
            toolBar.background = getDrawable(R.drawable.bg_toolbar_register)
        }*/
    }

    override fun switchFragment(
        fragment: KClass<*>,
        addToBackStack: Boolean,
        bundle: Bundle?
    ) {
        mFragmentBackStackManager.switchFragment(
            fragment,
            addToBackStack,
            bundle
        )
    }

    override fun setAppBarTitle(title: String) {
        tvTitleToolbar.text = title
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


/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }*/
}
//api key: 034bbd1b233d6726e0c7dc7f338657f9
//url: https://api.themoviedb.org/3/movie/popular?api_key=034bbd1b233d6726e0c7dc7f338657f9
//poster: https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg

