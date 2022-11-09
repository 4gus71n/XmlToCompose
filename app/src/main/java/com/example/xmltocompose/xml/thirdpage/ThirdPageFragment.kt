package com.example.xmltocompose.xml.thirdpage

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.xmltocompose.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ThirdPageFragment : Fragment() {

    private val webView: WebView
        get() = requireView().findViewById(R.id.fragmentThirdPageWebView)
    private val loadingOverlayView: View
        get() = requireView().findViewById(R.id.fragmentThirdPageLoadingOverview)
    private val refreshFab: FloatingActionButton
        get() = requireView().findViewById(R.id.fragmentThirdPageRefresh)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                loadingOverlayView.visibility = View.GONE
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                loadingOverlayView.visibility = View.VISIBLE
            }
        }
        refreshFab.setOnClickListener {
            webView.reload()
        }
        webView.loadUrl("www.google.com")
    }

    companion object {
        fun newInstance() = ThirdPageFragment()
    }
}