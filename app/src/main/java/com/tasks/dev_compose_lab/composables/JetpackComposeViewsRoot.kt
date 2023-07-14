package com.tasks.dev_compose_lab.composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nameisjayant.threadsapp.features.screens.HomeScreen
import com.nameisjayant.threadsapp.features.screens.PostScreen
import com.nameisjayant.threadsapp.features.screens.SearchScreen
import com.nameisjayant.threadsapp.utils.noRippleEffect
import com.tasks.dev_compose_lab.composables.screens.FavoriteScreen
import com.tasks.navigationcomponent.R
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme

class JetpackComposeViewsRoot : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //create Nav Controller
        setContent {

            NavigationComponentTheme {
             BottomNavigationBar()
            }

        }
    }

//    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun BottomNavigationBar(){
        val navHostController = rememberNavController()

        Scaffold(
            bottomBar = {
                MyBottomAppBar(controller = navHostController)
            }
        ) {

            Surface(modifier = Modifier.fillMaxSize(), color = Color.LightGray) {

                NavHost(
                    navController = navHostController,
                    startDestination = BottomBarItems.Favorite.route
                ) {
                    composable(com.nameisjayant.threadsapp.bottom_bar.BottomBarItems.Home.route) {
                        SearchScreen()
                    }
                    composable(BottomBarItems.Favorite.route) {
                        FavoriteScreen()
                    }
                    composable(BottomBarItems.Library.route) {
                        PostScreen()
                    }

                }
            }
        }
    }

    @Composable
    fun MyBottomAppBar(controller: NavHostController) {
        BottomAppBar(
            modifier = Modifier.height(60.dp),
            backgroundColor = Color.Black,
            contentColor = Color.DarkGray,
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {

            MyBottomBar(controller)
        }
    }

    @Composable
    fun MyBottomBar(controller: NavHostController) {


        val items: List<BottomBarItems> = arrayListOf(
            BottomBarItems.Home, BottomBarItems.Favorite, BottomBarItems.Library
        )

        val currentBackStackEntryAsState by controller.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntryAsState?.destination


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { screenItem ->
                BottomBarItemIcon(currentDestination, controller, Modifier, screenItem)
            }
        }

    }


    @Composable
    fun BottomBarItemIcon(
        currentDestination: NavDestination?,
        navHostController: NavHostController,
        modifier: Modifier,
        screen: BottomBarItems
    ) {


        val isSelected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } ?: true

        val contentColor = if (isSelected) Color.White else Color.DarkGray
        Box(modifier = Modifier.noRippleEffect {
            navHostController.navigate(screen.route) {

            }
        }) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = screen.icon),
                    contentDescription = "icon",
                    tint = contentColor,
                    modifier = Modifier.width(30.dp)
                        .height(30.dp)
                )
                Text(text = screen.title, color = contentColor, fontSize = 10.sp)
            }
        }

    }
}


sealed class BottomBarItems(val route: String, val title: String, val icon: Int) {

    object Home : BottomBarItems(
        "home", "Home", R.drawable.home
    )

    object Favorite : BottomBarItems(
        "favorite", "Favorite", R.drawable.love
    )


    object Library : BottomBarItems(
        "library", "Library", R.drawable.ic_baseline_library_music_24
    )


}