package com.thanmanhvinh.movieandnews.ui.main_2.intro

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SlidePagerAdapter(fragmentManager: FragmentManager?, val mResources: IntArray) : FragmentPagerAdapter(fragmentManager!!) {

    override fun getItem(position: Int): Fragment {
        return IntroPage().newInstance(position)
    }

    override fun getCount(): Int {
        return mResources.size
    }
}
