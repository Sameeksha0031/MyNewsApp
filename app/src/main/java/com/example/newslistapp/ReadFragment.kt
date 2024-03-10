package com.example.newslistapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.newslistapp.databinding.FragmentReadBinding

class ReadFragment : Fragment() {
    lateinit var binding : FragmentReadBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReadBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsUrl = arguments?.getString("selectedCard")
        Log.d("#sam","newsUrl = $newsUrl")
        binding.webView.loadUrl(newsUrl.toString())
        loadWebView()

    }

  //  @SuppressLint("SetJavaScriptEnabled")
    private fun loadWebView() {
        binding.webView.apply {
            settings.javaScriptEnabled = true
            settings.mediaPlaybackRequiresUserGesture = false
            settings.cacheMode = WebSettings.LOAD_NO_CACHE
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.setSupportZoom(false)
            settings.loadWithOverviewMode = true
            settings.domStorageEnabled = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    Log.d("#sam","request?.url. = ${request?.url.toString()}")
                    view?.loadUrl(request?.url.toString())
                    return false
                }
            }

            val cookieManager = CookieManager.getInstance()
            cookieManager.setAcceptThirdPartyCookies(binding.webView, true)
        }
    }
}