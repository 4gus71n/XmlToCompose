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
fun MapTerrainBottomSheetModal(sheetState: ModalBottomSheetState) {
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            MapTerrainBottomSheetBody()
        }
    ) {

    }
}

@Composable
fun MapTerrainBottomSheetBody() {
    ConstraintLayout(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
    ) {
        val (satImg, satTxt, terImg, terTxt) = createRefs()
        Image(
            modifier = Modifier
                .size(width = 64.dp, height = 64.dp)
                .constrainAs(satImg) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(terImg.start)
                    bottom.linkTo(satTxt.top)
                },
            painter = painterResource(id = R.drawable.ic_baseline_satellite_24),
            contentDescription = "Satellite Thumbnail"
        )
        Text(
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(satTxt) {
                    start.linkTo(satImg.start)
                    top.linkTo(satImg.bottom)
                    end.linkTo(satImg.end)
                    bottom.linkTo(parent.bottom)
                },
            text = "Satellite",
            style = MaterialTheme.typography.subtitle2
        )
        Image(
            modifier = Modifier
                .size(width = 64.dp, height = 64.dp)
                .constrainAs(terImg) {
                    start.linkTo(satImg.end)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(terTxt.top)
                },
            painter = painterResource(id = R.drawable.ic_baseline_terrain_24),
            contentDescription = "Terrain Thumbnail"
        )
        Text(
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(terTxt) {
                    start.linkTo(terImg.start)
                    top.linkTo(terImg.bottom)
                    end.linkTo(terImg.end)
                    bottom.linkTo(parent.bottom)
                },
            text = "Terrain",
            style = MaterialTheme.typography.subtitle2
        )
    }
}