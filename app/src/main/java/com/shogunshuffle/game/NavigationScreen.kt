package com.shogunshuffle.game

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationScreen(navHostController: NavHostController){
    NavHost(navController = navHostController,
        startDestination = Screens.Loading) {
        composable(Screens.Loading){
            LoadingScreen (
                onNext = {
                    navHostController.navigate(Screens.Menu)
                }
            )
        }

        composable(Screens.Menu){
            MenuScreen(
                onLevels = {
                    navHostController.navigate(Screens.Levels)
                },
                onOptions = {
                    navHostController.navigate(Screens.Options)
                },
                onExit = {
                    navHostController.navigate(Screens.Exit)
                }
            )
        }
        composable(Screens.Options){
            OptionsScreen(
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(Screens.Levels){
            LevelsScreen(
                onMenu = {
                    navHostController.navigate(Screens.Menu)
                }
            )
        }
        composable(Screens.Exit){
            ExitScreen(
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}