package com.example.xmltocompose.compose.firstpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.xmltocompose.R
import kotlin.math.roundToInt

@Composable
fun FirstPageScreen(modifier: Modifier) {
    // Agus https://stackoverflow.com/questions/67737502/how-to-detect-up-down-scroll-for-a-column-with-vertical-scroll

    val fabHeight = 72.dp //FabSize+Padding
    val fabHeightPx = with(LocalDensity.current) { fabHeight.roundToPx().toFloat() }
    val fabOffsetHeightPx = remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y
                val newOffset = fabOffsetHeightPx.value + delta
                fabOffsetHeightPx.value = newOffset.coerceIn(-fabHeightPx, 0f)

                return Offset.Zero
            }
        }
    }

    Scaffold(
        modifier = modifier.nestedScroll(nestedScrollConnection),
        floatingActionButton = {
            DiceFloatingActionButton(
                modifier = Modifier
                    .offset { IntOffset(x = 0, y = -fabOffsetHeightPx.value.roundToInt()) },
            )
        },
       floatingActionButtonPosition = FabPosition.End
    ) {
        ExampleList(Modifier.padding(it))
    }
}

@Composable
fun DiceFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit) = {}
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_casino_24),
            contentDescription = "Fab"
        )
    }
}

@Composable
@Preview
fun ExampleListDiceFabPreview() {
    DiceFloatingActionButton()
}

@Composable
fun ExampleList(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            (0..200).forEach {
                item {
                    ExampleEntry(it)
                }
            }
        }
    }
}

@Composable
fun ExampleEntry(exampleNumber: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "Example Title #$exampleNumber",
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "Lorem ipsum something long text  long text  long text  long text  long text  long text  long text ",
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
@Preview
fun FirstPageScreenPreview() {
    FirstPageScreen(Modifier)
}