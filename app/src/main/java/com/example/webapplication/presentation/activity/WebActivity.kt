package com.example.webapplication.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.webkit.*
import com.example.webapplication.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private var url: String = "url"
    private lateinit var binding: ActivityWebBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val actionBar = supportActionBar
        actionBar?.hide()
        setContentView(binding.root)

        webView = binding.webContainer

        val intentFromMa = intent
        url = intentFromMa.getStringExtra("URL").toString()

        webView.isClickable = true
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.loadUrl(url)

        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                webView: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {

                val intent = Intent(this@WebActivity, MainActivity::class.java)
                startActivity(intent)
            }

            // load url
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                view.loadUrl(url!!)
                return true
            }

            // save cookies
            override fun onPageFinished(webView: WebView, url: String) {
                CookieManager.getInstance().flush()
                CookieSyncManager.getInstance().startSync()
            }
        }

        webSettings.apply {
            domStorageEnabled = true
            saveFormData = true
            allowContentAccess = true
            allowFileAccess = true
            allowFileAccessFromFileURLs = true
            allowUniversalAccessFromFileURLs = true
            setSupportZoom(true)
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}