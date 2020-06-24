package com.thanmanhvinh.movieandnews.ui.base

import androidx.lifecycle.ViewModel
import com.thanmanhvinh.movieandnews.data.DataManager
import com.thanmanhvinh.movieandnews.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open abstract class BaseViewModel<I, O>: ViewModel() {

    lateinit var mDataManager: DataManager

    lateinit var mSchedulerProvider: SchedulerProvider

    //lateinit var mNavigatorFragment: INavigatorFragment

    lateinit var mNavigatorActivity: INavigatorActivity


    private val mCompositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    abstract fun transform(input: I): O

    open fun initData(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider
    ){
        this.mDataManager = dataManager
        this.mSchedulerProvider = schedulerProvider
    }

    override fun onCleared() {
        mCompositeDisposable.clear()
        super.onCleared()
    }

    fun Disposable.addToDisposable(){
        mCompositeDisposable.add(this)
    }

    fun onDestroyView(){
        mCompositeDisposable.clear()
    }


}