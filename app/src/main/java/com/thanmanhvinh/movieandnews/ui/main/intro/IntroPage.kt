package com.thanmanhvinh.movieandnews.ui.main.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thanmanhvinh.movieandnews.R
import kotlinx.android.synthetic.main.layout_intro.*

class IntroPage : Fragment() {
    var position = 0
    private val mResources = intArrayOf(R.drawable.angry4, R.drawable.angry4, R.drawable.angry4)

    fun newInstance(position: Int): IntroPage {
        val fragment = IntroPage()
        val arguments = Bundle()
        arguments.putInt("POSITION", position)
        fragment.arguments = arguments
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(R.layout.layout_intro, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args = arguments
        if (args != null) {
            position = args.getInt("POSITION")
        }

        introImage.setImageDrawable(resources.getDrawable(mResources[position]))
    }
}

