package com.shogunshuffle.game

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun NavigationScreen(navHostController: NavHostController){
    NavHost(navController = navHostController,
        startDestination = Screens.Loading) {
        composable(Screens.Loading){
            LoadingScreen (
                onNext = {
                    navHostController.navigate(Screens.Menu) {
                        popUpTo(Screens.Loading) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Screens.Menu){
            MenuScreen(
                onLevels = {
                    navHostController.navigate(Screens.Levels) {
                        launchSingleTop = true
                    }
                },
                onOptions = {
                    navHostController.navigate(Screens.Options) {
                        launchSingleTop = true
                    }
                },
                onExit = {
                    navHostController.navigate(Screens.Exit) {
                        launchSingleTop = true
                    }
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
                    navHostController.popBackStack()
                },
                onLevel = { level ->
                    navHostController.navigate(Screens.Game + "/${level}") {
                        launchSingleTop = true
                    }
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
        composable(Screens.Game + "/{level}",
            arguments = listOf( navArgument("level") { type = NavType.IntType }
            )) {

            val level = it.arguments?.getInt("level") ?: 1
            GameScreen(
                level = level,
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}