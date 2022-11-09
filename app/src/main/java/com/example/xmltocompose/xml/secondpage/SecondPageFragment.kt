package com.example.xmltocompose.xml.secondpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.xmltocompose.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SecondPageFragment : Fragment() {

    private val leftFab: FloatingActionButton
        get() = requireView().findViewById(R.id.fragmentSecondPageLeftFab)
    private val rightFab: FloatingActionButton
        get() = requireView().findViewById(R.id.fragmentSecondPageRightFab)
    private val bottomSheetBehavior by lazy {
        BottomSheetBehavior.from(requireView().findViewById(R.id.fragmentSecondPageBottomCardView))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetBehavior.isHideable = true
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        leftFab.setOnClickListener {
            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        rightFab.setOnClickListener {
            MapTerrainBottomSheetFragment().show(childFragmentManager, "test")
        }
    }

    companion object {
        fun newInstance() = SecondPageFragment()
    }
}