package com.thanmanhvinh.movieandnews.utils.extensions

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import java.text.DecimalFormat

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun Int.decimalFormat(): String {
    var pattern = "###,###,###,###.##"
    var decimalFormat = DecimalFormat(pattern)
    var format: String = decimalFormat.format(this)
    return format
}

fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}