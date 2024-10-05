package com.shogunshuffle.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun NavigationScreen(navHostController: NavHostController){
    val gameViewModel: GameViewModel = viewModel()
    val result by gameViewModel.result.collectAsState()
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
                    SoundManager.playSound()
                },
                onOptions = {
                    navHostController.navigate(Screens.Options) {
                        launchSingleTop = true
                    }
                    SoundManager.playSound()
                },
                onExit = {
                    navHostController.navigate(Screens.Exit) {
                        launchSingleTop = true
                    }
                    SoundManager.playSound()
                }
            )
        }
        composable(Screens.Options){
            OptionsScreen(
                onBack = {
                    navHostController.popBackStack()
                    SoundManager.playSound()
                }
            )
        }
        composable(Screens.Levels){
            LevelsScreen(
                onMenu = {
                    navHostController.popBackStack()
                    SoundManager.playSound()
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
                    SoundManager.playSound()
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
                    SoundManager.playSound()
                },
                onResult = {
                    navHostController.navigate(Screens.Result) {
                        launchSingleTop = true
                        popUpTo(Screens.Game + "/${level}") { inclusive = true }
                    }
                    SoundManager.playSound()
                },
                viewModel = gameViewModel
            )
        }

        composable(Screens.Result){
            ResultScreen(
                result = result,
                onBack = {
                    navHostController.popBackStack()
                    SoundManager.playSound()
                },
                onNextOrTryAgain = {
                    with(navHostController) {
                        if (result.isWin) {
                            navigate(Screens.Game + "/${result.lvl + 1}")
                        }
                        else {
                            navigate(Screens.Game + "/${result.lvl}")
                        }
                        SoundManager.playSound()
                    }
                }
            )
        }
    }
}