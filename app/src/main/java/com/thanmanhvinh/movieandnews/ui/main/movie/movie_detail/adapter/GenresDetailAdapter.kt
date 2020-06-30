package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieDetail

class GenresDetailAdapter(
    val context: Context?,
    val list: MutableList<MovieDetail.Genre>
): RecyclerView.Adapter<GenresDetailAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_genres, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItemGenres.text = list[position].name
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvItemGenres: TextView = itemView.findViewById(R.id.tvItemGenres)
    }

    fun upDateGenres(movie: MutableList<MovieDetail.Genre>){
        list.clear()
        list.addAll(movie)
        notifyDataSetChanged()
    }
}