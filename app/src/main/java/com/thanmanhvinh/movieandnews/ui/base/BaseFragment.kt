package com.thanmanhvinh.movieandnews.ui.base

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import com.thanmanhvinh.movieandnews.data.DataManager
import com.thanmanhvinh.movieandnews.di.module.ViewModelFactory
import com.thanmanhvinh.movieandnews.utils.rx.SchedulerProvider
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import kotlin.reflect.KClass


open abstract class BaseFragment<VM: BaseViewModel<*, *>>: DaggerFragment() {

    lateinit var mActivity: Activity

    lateinit var mNavigatorActivity: INavigatorActivity

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var schedulerProvider: SchedulerProvider
    @Inject
    lateinit var dataManager: DataManager

    private var compositeDisposable = CompositeDisposable()

    var mRootView: View? = null

    lateinit var mViewModel: VM

    private var mIsAttached: Boolean = false

    private var isFirst = true

    open var showToolBar: Boolean = true

    abstract fun createViewModel(): Class<VM>

    abstract fun getResourceLayout(): Int

    abstract fun getTitleActionBar(): Int

    abstract fun bindViewModel()

    abstract fun initData()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mIsAttached = true
        if (context is INavigatorActivity && isFirst) {
            mActivity = context as Activity
            mNavigatorActivity = context
            mViewModel = ViewModelFactory(schedulerProvider, dataManager).create(createViewModel())
            //mViewModel.mNavigatorFragment = this
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mNavigatorActivity.onFragmentResumed(this)
        if (mRootView == null) {
            mRootView = onCreateViewInternal(inflater, container)
        }
        setupUI(mRootView!!)
        return mRootView
    }

    private fun onCreateViewInternal(inflater: LayoutInflater, container: ViewGroup?): View {
        val resId = getResourceLayout()
        if (resId == 0) {
            throw RuntimeException("resource layout not set")
        }
        return inflater.inflate(resId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isFirst){
            bindViewModel()
            initData()
            isFirst = false
        }
    }

    private fun setupUI(view: View) {
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                hideSoftKeyboard()
                false
            }
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(innerView)
            }
        }
    }

    fun hideSoftKeyboard() {
        mActivity.currentFocus?.let {
            val inputMethodManager =
                mActivity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    open fun onButtonBackClick() {
        mNavigatorActivity.onBackPressed()
    }

    fun switchFragment(fragment: KClass<*>) {
        mNavigatorActivity.switchFragment(fragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onDetach() {
        super.onDetach()
        mIsAttached = false
    }

    fun Disposable.addToDisposable() {
        compositeDisposable.add(this)
    }


    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

}