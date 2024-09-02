package com.taemallah.quizz.utils

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object MainScreen: Route()
    @Serializable
    data object SoloScreen: Route()
    @Serializable
    data object MatchScreen: Route()
}