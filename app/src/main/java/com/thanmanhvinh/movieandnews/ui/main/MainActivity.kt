package com.thanmanhvinh.movieandnews.ui.main

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.navigation.findNavController
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseActivity
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.base.FragmentBackStackManager
import com.thanmanhvinh.movieandnews.ui.base.INavigatorActivity
import com.thanmanhvinh.movieandnews.ui.splash.SplashActivity
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
        //setSupportActionBar(toolBar)

        val bundle = intent.getBundleExtra(AppConstants.HOME)
/*        when{
            bundle != null -> switchFragment(MovieFragment::class, false)
        }*/
        //switchFragment(MovieFragment::class, false)

        //currentFragment.onButtonBackClick()


    }


    override fun bindViewModel() {
        checkInternet()

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

    //
    private fun checkInternet(): Unit {
        var connected = false
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .state == NetworkInfo.State.CONNECTED ||
            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .state == NetworkInfo.State.CONNECTED
        ) {
            connected = true
        } else {
            connected = false
            val intentNotConnect = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intentNotConnect)

            //
            
        }
    }

/*    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.menu_right, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logins ->{
                Toast.makeText(this, "login", Toast.LENGTH_SHORT).show()
            }
            R.id.aa ->{
                Toast.makeText(this, "aa", Toast.LENGTH_SHORT).show()
            }
            R.id.bb ->{
                Toast.makeText(this, "bb", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }*/


/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }*/
}
//api key: 034bbd1b233d6726e0c7dc7f338657f9
//url: https://api.themoviedb.org/3/movie/popular?api_key=034bbd1b233d6726e0c7dc7f338657f9
//poster: https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg

