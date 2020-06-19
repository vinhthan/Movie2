package com.thanmanhvinh.movieandnews.utils

import io.reactivex.Scheduler

interface SchedulerProvider {
    val computation: Scheduler
    val io: Scheduler
    val ui: Scheduler
}