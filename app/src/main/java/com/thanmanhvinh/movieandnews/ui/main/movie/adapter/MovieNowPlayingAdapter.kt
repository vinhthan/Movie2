package com.thanmanhvinh.movieandnews.ui.main.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieNowPlaying
import com.thanmanhvinh.movieandnews.utils.extensions.loadUrl

class MovieNowPlayingAdapter(
    val context: Context?,
    var list: MutableList<MovieNowPlaying.Results>,
    itemOnClickToDetail: ItemOnClickNowPlaying
): RecyclerView.Adapter<MovieNowPlayingAdapter.ViewHolder>() {

    private val onClick: ItemOnClickNowPlaying = itemOnClickToDetail

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgMovieNowPlaying: ImageView = itemView.findViewById(R.id.imgMovie)
        var tvTitleMovieNowPlaying: TextView = itemView.findViewById(R.id.tvTitleMovie)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitleMovieNowPlaying.text = list[position].title
        //context?.let { Glide.with(it).load(list[position].getImagePosterPathNowPlaying()).into(holder.imgMovieNowPlaying) }

        //use extension
        holder.imgMovieNowPlaying.loadUrl(list[position].getImagePosterPathNowPlaying())
        holder.itemView.setOnClickListener {
            onClick.OnItemClickNowPlaying(position)
        }

    }


    fun updateList(listMovie: MutableList<MovieNowPlaying.Results>){
        list.clear()
        list.addAll(listMovie)
        notifyDataSetChanged()
    }
}

