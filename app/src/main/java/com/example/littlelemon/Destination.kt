package com.example.littlelemon

interface Destination{
    val route: String
}
object OnBoarding: Destination {
    override val route = "OnBoarding"
}

object Home: Destination {
    override val route = "Home"
}

object Profile: Destination {
    override val route = "Profile"
}