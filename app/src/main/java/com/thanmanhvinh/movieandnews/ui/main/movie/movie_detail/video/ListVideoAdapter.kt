package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.video

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieVideo
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickNowPlaying
import com.thanmanhvinh.movieandnews.utils.common.AppConstants

class ListVideoAdapter(
    val context: Context?,
    private val listVideo: MutableList<MovieVideo.Result>,
    itemOnClickToDetail: ItemOnClickNowPlaying
): RecyclerView.Adapter<ListVideoAdapter.ViewHolder>() {

    private val onClick: ItemOnClickNowPlaying = itemOnClickToDetail

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listVideo.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var key = listVideo[position].key
        var linkVideo = AppConstants.LINK_YOU_TOBE + listVideo[position].key
        holder.tvLinkVideoYouTobe.text = linkVideo
        context?.let { Glide.with(it).load("https://i3.ytimg.com/vi/$key/hqdefault.jpg").into(holder.imgThumnaiVideo) }
        holder.tvTitleVideo.text = listVideo[position].name

        holder.itemView.setOnClickListener {
            onClick.OnItemClickNowPlaying(position)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgThumnaiVideo: ImageView = itemView.findViewById(R.id.imgThumnaiYouTobe)
        var tvTitleVideo: TextView = itemView.findViewById(R.id.tvTitleVideo)
        var tvLinkVideoYouTobe: TextView = itemView.findViewById(R.id.tvLinkVideoYouTobe)
        var tvKeyVideo: TextView = itemView.findViewById(R.id.tvKeyVideo)
    }

    fun updateVideo(movie: MutableList<MovieVideo.Result>){
        listVideo.clear()
        listVideo.addAll(movie)
        notifyDataSetChanged()
    }

}