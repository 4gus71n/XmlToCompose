package com.example.xmltocompose.xml

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.xmltocompose.R
import com.example.xmltocompose.xml.adapter.MainFragmentAdapter
import com.google.android.material.navigation.NavigationBarView

class XMLMainActivity : FragmentActivity() {

    private val adapter = MainFragmentAdapter(this)
    private val navigationView: NavigationBarView
        get() = findViewById(R.id.mainActivityNavigationView)
    private val viewPager: ViewPager2
        get() = findViewById(R.id.mainActivityViewPager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setupViews()
    }

    private fun setupViews() {
        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = false
        navigationView.setOnItemSelectedListener {
            val position = when (it.itemId) {
                R.id.menu_example_first_page -> 0
                R.id.menu_example_second_page -> 1
                R.id.menu_example_third_page -> 2
                else -> throw RuntimeException("Unknown menu item!")
            }
            viewPager.currentItem = position
            true
        }
    }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, XMLMainActivity::class.java)
        }
    }
}