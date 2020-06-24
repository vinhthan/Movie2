package com.thanmanhvinh.movieandnews.ui.main.movie.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieNowPlaying
import com.thanmanhvinh.movieandnews.utils.recyclerview.RecyclerAdapter
import kotlinx.android.synthetic.main.item_movie.view.*

/*
class MovieNowPlayingAdapter(
    val context: Context?,
    var list: MutableList<MovieNowPlaying.Results>,
    itemOnClickNowPlaying: ItemOnClickNowPlaying
): RecyclerView.Adapter<MovieNowPlayingAdapter.ViewHolder>() {

    private val onClick: ItemOnClickNowPlaying = itemOnClickNowPlaying

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgMovieNowPlaying: ImageView = itemView.findViewById(R.id.imgMovieNowPlaying)
        var tvTitleMovieNowPlaying: TextView = itemView.findViewById(R.id.tvTitleMovieNowPlaying)

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
        context?.let { Glide.with(it).load(list[position].getImagePosterPathNowPlaying()).into(holder.imgMovieNowPlaying) }

        holder.itemView.setOnClickListener {
            onClick.OnItemClickNowPlaying(position)
        }

    }


    fun UpdateList(listMovie: MutableList<MovieNowPlaying.Results>){
        list.clear()
        list.addAll(listMovie)
        notifyDataSetChanged()
    }
}*/

class MovieNowPlayingAdapter(
    val context: Context?,
    //val onClick: (MovieNowPlaying.Results) -> (Unit)
    itemOnClickNowPlaying: ItemOnClickNowPlaying
): RecyclerAdapter<MovieNowPlaying.Results>(context!!){

    private val onClick: ItemOnClickNowPlaying = itemOnClickNowPlaying

    override var layoutResource: Int = R.layout.item_movie

    override fun createViewHolder(view: View): RecyclerView.ViewHolder {
        return ViewHolder(view)
    }

/*    override fun binHolder(holder: RecyclerView.ViewHolder, position: Int) {
        *//*
        holder..text = list[position].title
        context?.let { Glide.with(it).load(list[position].getImagePosterPathNowPlaying()).into(holder.imgMovieNowPlaying) }

        holder.itemView.setOnClickListener {
            onClick.OnItemClickNowPlaying(position)
        }*//*

        if (holder is ViewHolder){
            holder.bind(getItem(position))
        }
    }*/

    override fun bindHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder){
            holder.bind(getItem(position))
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(movie: MovieNowPlaying.Results){
            itemView.setOnClickListener {
                //onClick.OnItemClickNowPlaying(position)
                onClick.OnItemClickNowPlaying(movie)
            }
            itemView.tvTitleMovieNowPlaying.text = movie.title
            context?.let { Glide.with(it).load(movie.getImagePosterPathNowPlaying()).into(itemView.imgMovieNowPlaying) }
        }
    }




/*    fun UpdateList(listMovie: MutableList<MovieNowPlaying.Results>){
        list.clear()
        list.addAll(listMovie)
        notifyDataSetChanged()
    }*/

}
