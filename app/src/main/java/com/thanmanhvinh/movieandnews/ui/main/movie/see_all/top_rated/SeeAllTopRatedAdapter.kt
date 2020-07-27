package com.thanmanhvinh.movieandnews.ui.main.movie.see_all.top_rated

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieTopRated
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickTopRated
import com.thanmanhvinh.movieandnews.utils.recyclerview.RecyclerAdapter
import kotlinx.android.synthetic.main.item_movie_see_all.view.*

class SeeAllTopRatedAdapter(
    val context: Context?,
    itemOnClickTopRated: ItemOnClickTopRated

): RecyclerAdapter<MovieTopRated.Results>(context!!) {

    private val onClick: ItemOnClickTopRated = itemOnClickTopRated

    override var layoutResource: Int = R.layout.item_movie_see_all

    override fun createViewHolder(view: View): RecyclerView.ViewHolder {
        return ViewHolder(view)
    }

    override fun bindHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder){
            holder.bind(getItem(position))
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(movie: MovieTopRated.Results){
            itemView.setOnClickListener {
                onClick.onItemClickTopRated(position)
            }
            itemView.tvTitleSeeAll.text = movie.title
            context?.let { Glide.with(it).load(movie.getImagePosterPathTopRated()).into(itemView.imgSeeAll) }
        }
    }
}