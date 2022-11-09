package com.example.xmltocompose.xml.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.xmltocompose.xml.firstpage.FirstPageFragment
import com.example.xmltocompose.xml.secondpage.SecondPageFragment
import com.example.xmltocompose.xml.thirdpage.ThirdPageFragment

class MainFragmentAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    private val fragments = listOf(
        FirstPageFragment.newInstance(),
        SecondPageFragment.newInstance(),
        ThirdPageFragment.newInstance()
    )

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}