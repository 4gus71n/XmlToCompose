package com.example.xmltocompose.xml.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xmltocompose.R

class ExampleViewHolder(v: View) : RecyclerView.ViewHolder(v)

class MockAdapter : RecyclerView.Adapter<ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
       return ExampleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_example, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        // NOOP
    }

    override fun getItemCount() = 200
}