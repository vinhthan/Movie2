package com.thanmanhvinh.movieandnews.ui.main

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.facebook.AccessToken
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseActivity
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.base.FragmentBackStackManager
import com.thanmanhvinh.movieandnews.ui.base.INavigatorActivity
import com.thanmanhvinh.movieandnews.ui.main_2.MainActivity2
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import kotlinx.android.synthetic.main.include_toolbar.*
import kotlin.reflect.KClass


class MainActivity : BaseActivity<MainViewModel>(), INavigatorActivity {

    override lateinit var currentFragment: BaseFragment<*>
    private lateinit var mFragmentBackStackManager: FragmentBackStackManager
    private lateinit var navController: NavController

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


        // NavigationView
/*        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawerLayout.closeDrawers()
            // Handle navigation view item clicks here.
            when (menuItem.itemId) {
                R.id.login1 -> {
                    switchFragment(LoginFragment::class, true, null)
                }
                R.id.logout1 -> {
                    toast("logout")
                }
                // Add code here to update the UI based on the item selected
                // For example, swap UI fragments here
            }
            true
        }

        imgMenu.setOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT)
        }*/

    }


    override fun bindViewModel() {
        checkInternet()
        navController = this.findNavController(R.id.container)

        /*imgSearch.setOnClickListener {
            navController.navigate(R.id.movieSearchFragment)
        }*/


        /**
         * get KeyHash facebook
         */
/*        try {
            val packageInfo = packageManager.getPackageInfo(
                "com.thanmanhvinh.movieandnews",
                GET_SIGNATURES
            )
            for (signature in packageInfo.signatures) {
                val messageDigest =
                    MessageDigest.getInstance("SHA")
                messageDigest.update(signature.toByteArray())
                Log.d(
                    "KeyHash",
                    Base64.encodeToString(
                        messageDigest.digest(),
                        Base64.DEFAULT
                    )
                )
            }
        } catch (e: Exception) {
        }*/

        /**
         * Facebook
         */
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired


    }


    override fun onFragmentResumed(fragment: BaseFragment<*>) {
        currentFragment = fragment
        /*toolBar.visibility = if (fragment.showToolBar) View.VISIBLE else View.GONE
        tvTitleToolbar.text = getString(fragment.getTitleActionBar())*/
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

    /**
     * Check internet
     */
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
            finish()

        }
    }


}

