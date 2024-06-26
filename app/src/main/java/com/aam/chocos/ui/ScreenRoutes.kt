package com.aam.chocos

sealed class ScreenRoutes(val route : String) {
    data object Splash : ScreenRoutes("_splash")
    data object Tutorial : ScreenRoutes("_splash")
    data object Login : ScreenRoutes("_splash")
    data object AddChoco : ScreenRoutes("_splash")
}