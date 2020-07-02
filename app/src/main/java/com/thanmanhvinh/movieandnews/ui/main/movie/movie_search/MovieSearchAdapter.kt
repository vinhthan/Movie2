package com.thanmanhvinh.movieandnews.ui.main.movie.movie_search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieSearch
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickNowPlaying

class MovieSearchAdapter(
    val context: Context?,
    val list: MutableList<MovieSearch.Result>,
    itemOnClickNowPlaying: ItemOnClickNowPlaying
) : RecyclerView.Adapter<MovieSearchAdapter.ViewHolder>(){

    private val onClick: ItemOnClickNowPlaying = itemOnClickNowPlaying

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_movie_search, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitleMovieSearch.text = list[position].title
        context?.let { Glide.with(it).load(list[position].getImagePosterPath()).into(holder.imgMovieSearch) }

        holder.itemView.setOnClickListener {
            onClick.OnItemClickNowPlaying(position)
        }

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvTitleMovieSearch: TextView = itemView.findViewById(R.id.tvTitleMovieSearch)
        var imgMovieSearch: ImageView = itemView.findViewById(R.id.imgMovieSearch)
    }

    fun updateListMovieSearch(movie: MutableList<MovieSearch.Result>){
        list.clear()
        list.addAll(movie)
        notifyDataSetChanged()
    }
}