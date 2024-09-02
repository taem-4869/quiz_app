package com.taemallah.quizz.mainLayer.presentation.matchScreen

sealed class Player {
    data object Blue: Player()
    data object Red: Player()
}