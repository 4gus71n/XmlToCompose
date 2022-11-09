package com.example.xmltocompose.xml.firstpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.xmltocompose.R
import com.example.xmltocompose.xml.adapter.MockAdapter

class FirstPageFragment : Fragment() {

    private val adapter = MockAdapter()
    private val recyclerView: RecyclerView
        get() = requireView().findViewById(R.id.fragmentFirstPageRecyclerView)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance() = FirstPageFragment()
    }
}