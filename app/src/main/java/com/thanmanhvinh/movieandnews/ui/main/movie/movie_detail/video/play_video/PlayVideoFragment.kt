package com.thanmanhvinh.movieandnews.ui.main.movie.movie_detail.video.play_video

import android.graphics.Bitmap
import android.os.Build
import android.view.View
import android.webkit.*
import com.thanmanhvinh.movieandnews.R
import com.thanmanhvinh.movieandnews.ui.base.BaseFragment
import com.thanmanhvinh.movieandnews.utils.common.AppConstants
import kotlinx.android.synthetic.main.fragment_play_video.*

class PlayVideoFragment : BaseFragment<PlayVideoViewModel>() {


    override fun createViewModel(): Class<PlayVideoViewModel> = PlayVideoViewModel::class.java

    override fun getResourceLayout(): Int = R.layout.fragment_play_video

    override fun getTitleActionBar(): Int = R.string.empty

    override fun bindViewModel() {

    }

    override fun initData() {
        val link = arguments?.getString(AppConstants.SEND_LINK)


        // Get the web view settings instance
        val settings = webViewPlayVideo.settings

        // Enable java script in web view
        settings.javaScriptEnabled = true

        // Enable and setup web view cache
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_DEFAULT


        // Enable zooming in web view
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = true


        // Zoom web view text
        //settings.textZoom = 125


        // Enable disable images in web view
        settings.blockNetworkImage = false
        // Whether the WebView should load image resources
        settings.loadsImagesAutomatically = true


        // More web view settings
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            settings.safeBrowsingEnabled = true  // api 26
        }
        //settings.pluginState = WebSettings.PluginState.ON
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.mediaPlaybackRequiresUserGesture = false

        // More optional settings, you can enable it by yourself
        settings.domStorageEnabled = true
        settings.setSupportMultipleWindows(true)
        settings.loadWithOverviewMode = true
        settings.allowContentAccess = true
        settings.setGeolocationEnabled(true)
        settings.allowUniversalAccessFromFileURLs = true

        settings.allowFileAccess = true

        // WebView settings
        webViewPlayVideo.fitsSystemWindows = true

        /* if SDK version is greater of 19 then activate hardware acceleration
        otherwise activate software acceleration  */

        webViewPlayVideo.setLayerType(View.LAYER_TYPE_HARDWARE, null)

        webViewPlayVideo.loadUrl(link)

        // Set web view client
        webViewPlayVideo.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                // Page loading started
                // Do something
            }

            override fun onPageFinished(view: WebView, url: String) {
                // Page loading finished
                // Enable disable back forward button
            }
        }

    }

}



