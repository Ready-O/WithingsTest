package com.reda.withingstest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.reda.withingstest.ui.images.ImagesScreen
import com.reda.withingstest.ui.search.SearchScreen
import com.reda.withingstest.ui.theme.WithingsTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WithingsTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "search_route",
                    ){
                        composable(
                            route = "search_route"
                        ){
                            SearchScreen(
                                navigateToImages = {navController.navigate("images_route/$it")}
                            )
                        }
                        composable(
                            route = "images_route/{searchQuery}",
                            arguments =  listOf(navArgument("searchQuery") {
                                nullable = false
                                type = NavType.StringType
                            }
                            )
                        ){
                            ImagesScreen()
                        }
                    }
                }
            }
        }
    }
}
