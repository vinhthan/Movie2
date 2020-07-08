package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieSimilar
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickNowPlaying

class SimilarAdapter(
    val context: Context?,
    val list: MutableList<MovieSimilar.Result>,
    itemOnClickNowPlaying: ItemOnClickNowPlaying
) : RecyclerView.Adapter<SimilarAdapter.ViewHolder>(){

    private val onClick: ItemOnClickNowPlaying = itemOnClickNowPlaying


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitleMovie.text = list[position].title
        context?.let { Glide.with(it).load(list[position].getImagePosterPath()).into(holder.imgMovie) }

        holder.itemView.setOnClickListener {
            onClick.OnItemClickNowPlaying(position)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvTitleMovie: TextView = itemView.findViewById(R.id.tvTitleMovie)
        var imgMovie: ImageView = itemView.findViewById(R.id.imgMovie)
    }

    fun updateSimilar(similar: MutableList<MovieSimilar.Result>){
        list.clear()
        list.addAll(similar)
        notifyDataSetChanged()

    }
}