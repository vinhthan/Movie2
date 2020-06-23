package com.thanmanhvinh.movieandnews.ui.main

import android.content.Intent
import android.os.Bundle
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseActivity
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.base.INavigatorActivity
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.upcoming_detail.UpcomingDetailFragment

class MainActivity : BaseActivity<MainViewModel>(), INavigatorActivity {

    override lateinit var currentFragment: BaseFragment<*>
    //private lateinit var mFragmentBackStackManager: FragmentBackStackManager

    override fun createViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun bindViewModel() {

    }


    override fun onFragmentResumed(fragment: BaseFragment<*>) {
        currentFragment = fragment
    }

/*    override fun switchFragment(
        fragment: KClass<*>,
        addToBackStack: Boolean,
        animation: Boolean,
        bundle: Bundle?
    ) {
        mFragmentBackStackManager.switchFragment(
            fragment,
            addToBackStack,
            animation,
            bundle
        )
    }*/

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



/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }*/
}
//api key: 034bbd1b233d6726e0c7dc7f338657f9
//url: https://api.themoviedb.org/3/movie/popular?api_key=034bbd1b233d6726e0c7dc7f338657f9
//poster: https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg

