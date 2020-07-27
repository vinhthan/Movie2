package com.thanmanhvinh.movieandnews.ui.main.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieTopRated
import com.thanmanhvinh.movieandnews.utils.extensions.loadUrl

class MovieTopRatedAdapter(
    val context: Context?,
    var list: MutableList<MovieTopRated.Results>,
    itemOnClick: ItemOnClickTopRated
): RecyclerView.Adapter<MovieTopRatedAdapter.ViewHolder>() {

    private val onClick: ItemOnClickTopRated = itemOnClick

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleTopRated: TextView = itemView.findViewById(R.id.tvTitleMovie)
        val imgTopRated: ImageView = itemView.findViewById(R.id.imgMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTopRatedAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieTopRatedAdapter.ViewHolder, position: Int) {
        holder.titleTopRated.text = list[position].title
        //context?.let { Glide.with(it).load(list[position].getImagePosterPathTopRated()).into(holder.imgTopRated) }
        holder.imgTopRated.loadUrl(list[position].getImagePosterPathTopRated())

        holder.itemView.setOnClickListener {
            onClick.onItemClickTopRated(position)
        }
    }

    fun updateList(listMovie: MutableList<MovieTopRated.Results>){
        list.clear()
        list.addAll(listMovie)
        notifyDataSetChanged()
    }
}