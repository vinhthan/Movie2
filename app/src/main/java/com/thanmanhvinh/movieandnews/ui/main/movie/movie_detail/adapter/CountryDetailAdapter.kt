package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieDetail
import kotlinx.android.synthetic.main.item_countries.view.*

class CountryDetailAdapter(
    val context: Context?,
    private val listCountries: MutableList<MovieDetail.ProductionCountry>
) : RecyclerView.Adapter<CountryDetailAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_countries, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCountries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCountryDetail.text = listCountries[position].iso31661
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvCountryDetail: TextView = itemView.findViewById(R.id.tvItemCountry)
    }

    fun updateCountries(countries: MutableList<MovieDetail.ProductionCountry>){
        listCountries.clear()
        listCountries.addAll(countries)
        notifyDataSetChanged()
    }
}