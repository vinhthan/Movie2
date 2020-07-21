package com.thanmanhvinh.movieandnews.ui.main.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieUpcoming
import com.thanmanhvinh.movieandnews.ui.base.BaseRecyclerView
import com.thanmanhvinh.movieandnews.utils.extensions.loadUrl
import kotlinx.android.synthetic.main.layout2.view.*

class SlideAdapter: BaseRecyclerView<MovieUpcoming.Result>(){

    private val movie : MutableList<MovieUpcoming.Result> by lazy { mutableListOf<MovieUpcoming.Result>() }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout2, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movie.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder){
            holder.bind(movie[position])
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(movie: MovieUpcoming.Result){
            itemView.imageView16.loadUrl(movie.getImageBackdropPathUpcoming())
        }
    }

    fun set(list: MutableList<MovieUpcoming.Result>){
        list.clear()
        list.addAll(list)
        notifyDataSetChanged()
    }

}
