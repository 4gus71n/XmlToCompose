package com.example.xmltocompose

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.xmltocompose.compose.ComposeMainActivity
import com.example.xmltocompose.xml.XMLMainActivity

class HubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub)

        findViewById<View>(R.id.activityHubComposeBtn).setOnClickListener {
            startActivity(ComposeMainActivity.getStartIntent(this@HubActivity))
        }
        findViewById<View>(R.id.activityHubXMLBtn).setOnClickListener {
            startActivity(XMLMainActivity.getStartIntent(this@HubActivity))
        }
    }
}