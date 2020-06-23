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
import com.thanmanhvinh.movieandnews.data.api.MoviePopular

class MoviePopularAdapter(
    val context: Context?,
    var list: MutableList<MoviePopular.Result>,
    itemOnClick: ItemOnClickPopular
): RecyclerView.Adapter<MoviePopularAdapter.ViewHolder>() {

    private val onClick: ItemOnClickPopular = itemOnClick

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var titlePopular: TextView = itemView.findViewById(R.id.tvTitleMovieNowPlaying)
        var imgPopular: ImageView = itemView.findViewById(R.id.imgMovieNowPlaying)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePopularAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MoviePopularAdapter.ViewHolder, position: Int) {
        holder.titlePopular.text = list[position].title
        context?.let { Glide.with(it).load(list[position].getImagePosterPathPopular()).into(holder.imgPopular) }

        holder.itemView.setOnClickListener {
            onClick.OnItemClickPopular(position)
        }
    }

    fun UpdateList(listMovie: MutableList<MoviePopular.Result>){
        list.clear()
        list.addAll(listMovie)
        notifyDataSetChanged()
    }
}