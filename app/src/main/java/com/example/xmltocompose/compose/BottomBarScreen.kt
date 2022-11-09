package com.example.xmltocompose.compose

import com.example.xmltocompose.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object FirstPage : BottomBarScreen(
        route = "first_page",
        title = "First",
        icon = R.drawable.ic_baseline_auto_fix_normal_24
    )

    object SecondPage : BottomBarScreen(
        route = "second_page",
        title = "Second",
        icon = R.drawable.ic_baseline_bakery_dining_24
    )

    object ThirdPage : BottomBarScreen(
        route = "third_page",
        title = "Third",
        icon = R.drawable.ic_baseline_battery_saver_24
    )
}