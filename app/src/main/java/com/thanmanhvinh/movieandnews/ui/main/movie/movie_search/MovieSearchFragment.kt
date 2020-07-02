package com.thanmanhvinh.movieandnews.ui.main.movie.movie_search

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieSearch
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickNowPlaying
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_movie_search.*
import kotlinx.android.synthetic.main.include_toolbar.*
import java.util.concurrent.TimeUnit


class MovieSearchFragment : BaseFragment<MovieSearchViewModel>(), ItemOnClickNowPlaying {

    private lateinit var mList: MutableList<MovieSearch.Result>
    private lateinit var mAdapter: MovieSearchAdapter

    val triggerSearch = PublishSubject.create<Unit>()



    override fun createViewModel(): Class<MovieSearchViewModel> = MovieSearchViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_movie_search

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

        val output = mViewModel.transform(
            MovieSearchViewModel.Input(
                edtSearch.textChanges().map { it.toString() },
                imgGotoSearch.clicks().throttleFirst(300, TimeUnit.MILLISECONDS)
            )
        )

        with(output){
            loadList.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapter.updateListMovieSearch(list)
                }.addToDisposable()
        }
    }

    override fun initData() {
        layout_search.visibility = View.VISIBLE
        layout_discover.visibility = View.GONE



        //showMovieSearch()
/*        imgGotoSearch.setOnClickListener {
           *//* showMovieSearch()
            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()*//*
            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
            //triggerSearch.onNext(Unit)
        }*/
    }

    private fun showMovieSearch(){
        mList = mutableListOf()
        mAdapter = MovieSearchAdapter(context, mList, this)
        rcyMovieSearch.setHasFixedSize(true)
        rcyMovieSearch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcyMovieSearch.adapter = mAdapter
    }

    override fun OnItemClickNowPlaying(position: Int) {
        TODO("Not yet implemented")
    }


}