package com.thanmanhvinh.movieandnews.ui.main.movie.see_all.now_playing

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieNowPlaying
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickNowPlaying
import com.thanmanhvinh.movieandnews.utils.recyclerview.RecyclerAdapter
import kotlinx.android.synthetic.main.item_movie_see_all.view.*

class SeeAllNowPlayingAdapter(
    val context: Context?,
    //val onClick: (MovieNowPlaying.Results) -> (Unit)
    itemOnClickNowPlaying: ItemOnClickNowPlaying
): RecyclerAdapter<MovieNowPlaying.Results>(context!!){

    private val onClick: ItemOnClickNowPlaying = itemOnClickNowPlaying

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
        fun bind(movie: MovieNowPlaying.Results){
            itemView.setOnClickListener {
                onClick.onItemClickNowPlaying(position)
            }
            itemView.tvTitleSeeAll.text = movie.title
            context?.let { Glide.with(it).load(movie.getImagePosterPathNowPlaying()).into(itemView.imgSeeAll) }
        }
    }

}