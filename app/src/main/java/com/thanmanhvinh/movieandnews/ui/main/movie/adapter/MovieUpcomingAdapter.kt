package com.thanmanhvinh.movieandnews.ui.main.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieUpcoming
import com.thanmanhvinh.movieandnews.utils.extensions.loadUrl

class MovieUpcomingAdapter(
    val context: Context?,
    var list: MutableList<MovieUpcoming.Result>,
    itemOnClick: ItemOnClickUpcoming
) : RecyclerView.Adapter<MovieUpcomingAdapter.ViewHolder>(){

    private val onClick: ItemOnClickUpcoming = itemOnClick

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgUpcoming: ImageView = itemView.findViewById(R.id.imgMovie)
        var titleUpcoming: TextView = itemView.findViewById(R.id.tvTitleMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleUpcoming.text = list[position].title
        //context?.let { Glide.with(it).load(list[position].getImagePosterPathUpcoming()).into(holder.imgUpcoming) }
        holder.imgUpcoming.loadUrl(list[position].getImagePosterPathUpcoming())

        holder.itemView.setOnClickListener {
            onClick.onItemClickUpcoming(position)
        }
    }

    fun updateList(listMovie: MutableList<MovieUpcoming.Result>){
        list.clear()
        list.addAll(listMovie)
        notifyDataSetChanged()
    }
}