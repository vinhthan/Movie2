package com.thanmanhvinh.movieandnews.utils

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers


class AppSchedulerProvider :
    SchedulerProvider {
    override val computation: Scheduler
        get() = Schedulers.computation()
    override val io: Scheduler
        get() = Schedulers.io()
    override val ui: Scheduler
        get() = AndroidSchedulers.mainThread()

}