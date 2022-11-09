package com.example.xmltocompose.xml.secondpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xmltocompose.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MapTerrainBottomSheetFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_example, container, false)
    }
}