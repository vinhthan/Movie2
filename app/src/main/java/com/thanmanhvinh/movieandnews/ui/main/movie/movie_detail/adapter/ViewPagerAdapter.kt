package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.thanmanhvinh.movieandnews.data.AppDataManager_Factory.newInstance
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.review_similar.review.ReviewFragment
import com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.review_similar.similar.SimilarFragment


/*class ViewPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val COUNT = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = ReviewFragment()
            1 -> fragment = SimilarFragment()
        }

        return fragment!!
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab " + (position + 1)
    }
}*/

private class ViewPagerAdapter(fm: FragmentManager?) :
    FragmentPagerAdapter(fm!!) {
    override fun getItem(pos: Int): Fragment {
        return when (pos) {
            0 -> ReviewFragment()
            1 -> SimilarFragment()
            else -> ReviewFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}
