package com.example.loadingwebview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.loadingwebview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var url : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(::url.isInitialized) {
            loadWebView()
        }
    }

    fun setLoadWebView(url : String) {
        this.url = url
    }

    fun loadWebView() {
        binding.libraryWebView.loadUrl(url)
        binding.libraryWebView.apply {
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
                    view?.loadUrl(request?.url.toString())
                    return false
                }
            }

            val cookieManager = CookieManager.getInstance()
            cookieManager.setAcceptThirdPartyCookies(binding.libraryWebView, true)

        }
    }
}