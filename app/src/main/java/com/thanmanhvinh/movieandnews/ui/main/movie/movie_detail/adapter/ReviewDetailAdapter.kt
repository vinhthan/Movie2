package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieReview
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import com.thanmanhvinh.movieandnews.utils.extensions.toast


class ReviewDetailAdapter(
    val context: Context?,
    val list: MutableList<MovieReview.Result>
) : RecyclerView.Adapter<ReviewDetailAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_review, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvAuthor.text = list[position].author
        holder.tvContentReview.text = list[position].content
        holder.tvContentReview.maxLines = 2

        holder.tvViewAllReview.setOnClickListener {
            var tv: String = holder.tvViewAllReview.text.toString()
            if (tv == AppConstants.VIEW_ALL) {
                holder.tvContentReview.maxLines = Int.MAX_VALUE
                holder.tvViewAllReview.text = context?.getString(R.string.collapse)
            } else {
                holder.tvContentReview.maxLines = 2
                holder.tvViewAllReview.text = context?.getString(R.string.view_all)
            }

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        var tvContentReview: TextView = itemView.findViewById(R.id.tvContentReview)
        //var tvCollapseReview: TextView = itemView.findViewById(R.id.tvCollapseReview)
        var tvViewAllReview: TextView = itemView.findViewById(R.id.tvViewAllReview)
        var parent: ConstraintLayout = itemView.findViewById(R.id.parent)

    }

    fun updateReview(review: MutableList<MovieReview.Result>) {
        list.clear()
        list.addAll(review)
        notifyDataSetChanged()
    }


}
