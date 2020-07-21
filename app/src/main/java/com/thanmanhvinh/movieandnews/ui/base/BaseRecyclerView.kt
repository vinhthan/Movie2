package com.thanmanhvinh.movieandnews.ui.base

import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

abstract class BaseRecyclerView<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var weakReference: WeakReference<ActionUserListener<T>>? = null
    var listener: ActionUserListener<T>?
        get() {
            return weakReference?.get()
        }
        set(value) {
            weakReference = if (value != null) {
                WeakReference(value)
            } else {
                null
            }
        }

    interface ActionUserListener<T>{
        fun select(t: T?)
    }
}