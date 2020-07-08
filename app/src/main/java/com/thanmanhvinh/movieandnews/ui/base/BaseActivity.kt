package com.thanmanhvinh.movieandnews.ui.base

import android.os.Bundle
import com.thanmanhvinh.movieandnews.data.DataManager
import com.thanmanhvinh.movieandnews.di.module.ViewModelFactory
import com.thanmanhvinh.movieandnews.utils.rx.SchedulerProvider
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject

open abstract class BaseActivity<VM: BaseViewModel<*, *>>: DaggerAppCompatActivity() {

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    @Inject
    lateinit var dataManager: DataManager

    private val compositeDisposable = CompositeDisposable()

    lateinit var viewModel: VM

    abstract fun createViewModel(): Class<VM>

    abstract fun getContentView(): Int

    abstract fun initView()

    abstract fun bindViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelFactory(schedulerProvider, dataManager).create(createViewModel())
        setContentView(getContentView())
        //setSupportActionBar(toolBar)
        initView()
        bindViewModel()

    }

    fun Disposable.addToDisosable(){
        compositeDisposable.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }





}