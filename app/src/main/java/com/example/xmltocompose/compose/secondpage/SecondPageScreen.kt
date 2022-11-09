package com.example.xmltocompose.compose.secondpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.xmltocompose.R
import com.example.xmltocompose.compose.firstpage.DiceFloatingActionButton
import com.google.maps.android.compose.GoogleMap
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SecondPageScreen(
    onSheetFabClicked: (() -> Unit) = {},
    modifier: Modifier,
    isPreview: Boolean = false
) {
    val coroutineScope = rememberCoroutineScope()

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed
        )
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            HiThereSheetCard()
        },
        modifier = modifier,
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            BottomFloatingActionButtons(
                modifier = modifier,
                leftOnClick = {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    }
                },
                rightOnClick = onSheetFabClicked,
            )
        },
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Composable
fun HiThereSheetCard() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(color = Color.Transparent),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight())
        {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Hi there!",
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
fun BottomFloatingActionButtons(
    modifier: Modifier,
    leftOnClick: (() -> Unit) = {},
    rightOnClick: (() -> Unit) = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DiceFloatingActionButton(
            onClick = leftOnClick
        )
        Spacer(modifier = Modifier.weight(1f))
        DiceFloatingActionButton(
            onClick = rightOnClick
        )
    }
}

@Composable
@Preview()
fun MapTerrainBottomSheetBodyPreview() {
    MapTerrainBottomSheetBody()
}

@Composable
@Preview()
fun BottomFloatingActionButtonsPreview() {
    BottomFloatingActionButtons(Modifier)
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun SecondPageScreenPreview() {
    SecondPageScreen({}, Modifier, true)
}