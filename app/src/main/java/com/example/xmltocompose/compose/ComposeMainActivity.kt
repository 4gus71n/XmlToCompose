package com.example.xmltocompose.compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.xmltocompose.compose.ui.theme.XmlToComposeTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.xmltocompose.compose.firstpage.FirstPageScreen
import com.example.xmltocompose.compose.secondpage.MapTerrainBottomSheetModal
import com.example.xmltocompose.compose.secondpage.SecondPageScreen
import com.example.xmltocompose.compose.thirdpage.ThirdPageScreen
import kotlinx.coroutines.launch

class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XmlToComposeTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, ComposeMainActivity::class.java)
        }
    }
}

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier,
    onSheetFabClicked: (() -> Unit) = {}
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.FirstPage.route
    ) {
        composable(route = BottomBarScreen.FirstPage.route) {
            FirstPageScreen(modifier)
        }
        composable(route = BottomBarScreen.SecondPage.route) {
            SecondPageScreen(onSheetFabClicked, modifier)
        }
        composable(route = BottomBarScreen.ThirdPage.route) {
            ThirdPageScreen(modifier)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            bottomBar = { BottomBar(navController = navController) },
            topBar = { TopAppBar(
                title = {
                    Text("Compose")
                })
            }
        ) {
            BottomNavGraph(
                navController = navController,
                modifier = Modifier.padding(it),
                onSheetFabClicked = {
                    coroutineScope.launch {
                        modalBottomSheetState.show()
                    }
                }
            )
        }
        MapTerrainBottomSheetModal(
            sheetState = modalBottomSheetState
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.FirstPage,
        BottomBarScreen.SecondPage,
        BottomBarScreen.ThirdPage,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation {
        screens.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title,
                    fontSize = 9.sp) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    XmlToComposeTheme {
        // A surface container using the 'background' color from the theme
        MainScreen()
    }
}