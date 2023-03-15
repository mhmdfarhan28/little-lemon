package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationComposable(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = OnBoarding.route
    ){
        composable(Home.route){
            Home()
        }
        composable(OnBoarding.route){
            OnBoarding()
        }
        composable(Profile.route){
            Profile()
        }
    }
}