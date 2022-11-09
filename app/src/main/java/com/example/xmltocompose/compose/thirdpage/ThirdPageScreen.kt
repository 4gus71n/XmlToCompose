package com.example.xmltocompose.compose.thirdpage

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.xmltocompose.compose.firstpage.DiceFloatingActionButton

@Composable
fun ThirdPageScreen(
    modifier: Modifier
) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val shouldShowLoadingOverlay = remember {
            mutableStateOf(false)
        }
        val (fab) = createRefs()

        AndroidView(
            factory = {
                WebView(it).apply {
                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            super.onPageStarted(view, url, favicon)
                            shouldShowLoadingOverlay.value = true
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            shouldShowLoadingOverlay.value = false
                        }
                    }
                    loadUrl("www.google.com")
                }
            },
            update = {
                it.loadUrl("www.google.com")
            },
            modifier = Modifier
                .fillMaxSize()
        )

        DiceFloatingActionButton(modifier = Modifier
            .wrapContentSize()
            .constrainAs(fab) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            },
            onClick = {

            }
        )

        if (shouldShowLoadingOverlay.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xCC000000)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
@Preview
fun ThirdPageScreenPreview() {
    ThirdPageScreen(Modifier)
}