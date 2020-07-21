package com.thanmanhvinh.movieandnews.ui.main.intro

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import kotlinx.android.synthetic.main.fragment_intro.*

class IntroFragment : BaseFragment<IntroViewModel>(), View.OnClickListener{
    //
    val mResources = intArrayOf(R.drawable.angry4, R.drawable.angry4, R.drawable.angry4)
    lateinit var adapter: SlidePagerAdapter
    var currentTab = 0
    var tabCount = 0

    override fun createViewModel(): Class<IntroViewModel> = IntroViewModel::class.java

    override fun getResourceLayout(): Int  = R.layout.fragment_intro

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {
        //
        tabCount = mResources.size
        adapter = SlidePagerAdapter(fragmentManager, mResources)
        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

        })
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                currentTab = position + 1
                if (currentTab == tabCount) {
                    tvSkip.text = getString(R.string.continues)
                } else {
                    tvSkip.text = getString(R.string.skip)
                }
            }

            override fun onPageSelected(position: Int) {
            }

        })

        tvNext.setOnClickListener(this)
        tvSkip.setOnClickListener(this)
    }

    override fun initData() {
        /*val prefs = this.requireActivity().getSharedPreferences("Prefe", Context.MODE_PRIVATE)
        val firstStart = prefs.getString("firstStart", "")
        if (firstStart.equals("yes")){
            findNavController().navigate(R.id.movieFragment)
        }else{
            val editor = prefs.edit()
            editor.putString("firstStart", "yes")
            editor.apply()
        }*/

        if (dataManager.isFirst == AppConstants.YES){
            findNavController().navigate(R.id.movieFragment)
        }else{
            dataManager.setIsFirst(AppConstants.YES)
        }

    }

    //
/*    var position = 0

    fun newInstance(position: Int): IntroFragment {
        val fragment = IntroFragment()
        val arguments = Bundle()
        arguments.putInt("POSITION", position)
        fragment.arguments = arguments
        return fragment
    }*/

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvNext -> {
                if (currentTab == tabCount) {
                    tvSkip.text = getString(R.string.continues)
                } else {
                    tvSkip.text = getString(R.string.skip)
                    viewPager.currentItem = currentTab
                }
            }
            R.id.tvSkip -> {
                findNavController().navigate(R.id.movieFragment)
            }
        }
    }

}