package com.thanmanhvinh.movieandnews.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.main.movie.MovieFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.login.LoginFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.now_playing_detail.NowPlayingDetailFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.popular_detail.PopularDetailFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.top_rated_detail.TopRatedDetailFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.upcoming_detail.UpcomingDetailFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.video.ListVideoFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.video.play_video.PlayVideoFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_search.MovieSearchFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.see_all.now_playing.SeeAllNowPlayingFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.see_all.popular.SeeAllPopularFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.see_all.top_rated.SeeAllTopRatedFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.see_all.upcoming.SeeAllUpcomingFragment
import com.thanmanhvinh.movieandnews.ui.main.news.NewsFragment
import java.lang.Exception
import kotlin.reflect.KClass

class FragmentBackStackManager(private val supportFragmentManager: FragmentManager) {

    private fun getInstanceFragment(fragment: KClass<*>): Fragment{
        return when(fragment){

            MovieFragment::class -> MovieFragment()
            NewsFragment::class -> NewsFragment()
            NowPlayingDetailFragment::class -> NowPlayingDetailFragment()
            UpcomingDetailFragment::class -> UpcomingDetailFragment()
            TopRatedDetailFragment::class -> TopRatedDetailFragment()
            PopularDetailFragment::class -> PopularDetailFragment()
            MovieSearchFragment::class -> MovieSearchFragment()
            SeeAllNowPlayingFragment::class -> SeeAllNowPlayingFragment()
            SeeAllPopularFragment::class -> SeeAllPopularFragment()
            SeeAllTopRatedFragment::class -> SeeAllTopRatedFragment()
            SeeAllUpcomingFragment::class -> SeeAllUpcomingFragment()
            ListVideoFragment::class -> ListVideoFragment()
            PlayVideoFragment::class -> PlayVideoFragment()
            LoginFragment::class -> LoginFragment()


            else -> throw Exception("fragment not found")
        }
    }

    fun switchFragment(
        fragment: KClass<*>,
        addToBackStack: Boolean,
        bundle: Bundle?
    ) {
        val instanceFragment = getInstanceFragment(fragment)
        bundle?.let {
            instanceFragment.arguments = it
        }
        val ft = supportFragmentManager.beginTransaction()

        val tag = instanceFragment.tag
        ft.replace(R.id.container, instanceFragment, tag)
        if (addToBackStack) {
            ft.addToBackStack(tag)
        }
        ft.commit()
    }

}