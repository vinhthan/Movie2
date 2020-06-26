package com.thanmanhvinh.movieandnews.ui.base

import android.os.Bundle
import kotlin.reflect.KClass

interface INavigatorActivity {
    var currentFragment: BaseFragment<*>

    fun onFragmentResumed(fragment: BaseFragment<*>)

    fun switchFragment(
        fragment: KClass<*>,
        addToBackStack: Boolean = true,
        bundle: Bundle? = null
    )

    fun setAppBarTitle(title: String)

    fun onBackPressed(bundle: Bundle? = null)

    fun showActivity(activity: Class<*>, key: String? = null, bundle: Bundle? = null)
}