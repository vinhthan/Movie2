package com.thanmanhvinh.movieandnews.ui.main.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieUpcoming
import com.thanmanhvinh.movieandnews.ui.base.BaseRecyclerView
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickViewPager
import com.thanmanhvinh.movieandnews.utils.extensions.loadUrl
import kotlinx.android.synthetic.main.item_movie_home.view.*

class PagerAdapter(
    val context: Context,
    val list: MutableList<MovieUpcoming.Result>,
    itemClickViewPager: ItemOnClickViewPager
) : RecyclerView.Adapter<PagerAdapter.ViewHolder>() {

    private val onClick: ItemOnClickViewPager = itemClickViewPager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_movie_home, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgItemMovieHome.loadUrl(list[position].getImageBackdropPathUpcoming())
        holder.itemView.setOnClickListener {
            onClick.onItemClickViewPager(position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgItemMovieHome: ImageView = itemView.imgItemMovieHome.findViewById<ImageView>(R.id.imgItemMovieHome)
    }

    fun updateItemMovie(movie: MutableList<MovieUpcoming.Result>) {
        list.clear()
        list.addAll(movie)
        notifyDataSetChanged()
    }
}

/*class PagerAdapter(val context: Context) : RecyclerView.Adapter<PagerAdapter.ViewHolder>() {
    private var list = intArrayOf(R.drawable.angry4, R.drawable.angry4, R.drawable.angry4, R.drawable.angry4, R.drawable.angry4)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_movie_home, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgItemMovieHome.setImageDrawable(getDrawable(context, list[position]))
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgItemMovieHome: ImageView = itemView.imgItemMovieHome.findViewById(R.id.imgItemMovieHome)
    }

}*/


