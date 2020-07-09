package com.thanmanhvinh.movieandnews.ui.main.movie.movie_search

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.data.api.MovieSearch
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.adapter.ItemOnClickNowPlaying
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_movie_search.*
import kotlinx.android.synthetic.main.include_toolbar.*
import java.util.concurrent.TimeUnit


class MovieSearchFragment : BaseFragment<MovieSearchViewModel>(), ItemOnClickNowPlaying {

    private lateinit var mList: MutableList<MovieSearch.Result>
    private lateinit var mAdapter: MovieSearchAdapter
    private var triggerSearch = PublishSubject.create<Unit>()

    override fun createViewModel(): Class<MovieSearchViewModel> = MovieSearchViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_movie_search

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

/*        edtSearch.textChanges()
            .subscribe(
                { Toast.makeText(context, "change", Toast.LENGTH_SHORT).show() },
                { Log.e("MainActivity", "$it") }
            ).addToDisposable()*/

        val output = mViewModel.transform(
            MovieSearchViewModel.Input(
                edtSearch.textChanges().map { it.toString() },
                triggerSearch = triggerSearch
            )
        )

        imgSearchMovie.setOnClickListener {
            triggerSearch.onNext(Unit)
        }

        with(output) {
            loadList.observeOn(schedulerProvider.ui)
                .subscribe { list ->
                    mAdapter.updateListMovieSearch(list)
                    rcyMovieSearch.scrollToPosition(0)// Each search will return to the first position
                }
            errorToast.observeOn(schedulerProvider.ui)
                .subscribe {
                    Toast.makeText(context, R.string.please_try_again, Toast.LENGTH_SHORT).show()
                }

        }.addToDisposable()

        /**
         * listen to the event text changes continuously
         */
        edtSearch.textChanges().subscribe {
            Handler().postDelayed({
                triggerSearch.onNext(Unit)
            }, 100)

        }.addToDisposable()

        /**
         * search with software keyboard
         */
        edtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // Do what you want here
                //Toast.makeText(context, "click search", Toast.LENGTH_SHORT).show()
                triggerSearch.onNext(Unit)

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    override fun initData() {
        layout_search.visibility = View.VISIBLE
        layout_discover.visibility = View.GONE

        showMovieSearch()

        imgBack.setOnClickListener {
            onButtonBackClick()
        }

        tvCancel.setOnClickListener {
            edtSearch.setText("")
        }

        edtSearch.addTextChangedListener {
            imgSearchMovie.visibility = View.VISIBLE
            tvCancel.visibility = View.VISIBLE
        }

    }

    private fun showMovieSearch() {
        mList = mutableListOf()
        mAdapter = MovieSearchAdapter(context, mList, this)
        rcyMovieSearch.setHasFixedSize(true)
        rcyMovieSearch.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcyMovieSearch.adapter = mAdapter

    }

    override fun OnItemClickNowPlaying(position: Int) {
        val bundle = Bundle()
        if (mList.size > 0) {
            val movieId = mList[position].id
            bundle.putInt(AppConstants.ID_MOVIE, movieId)
        }
        findNavController().navigate(R.id.nowPlayingDetailFragment, bundle)
    }


}