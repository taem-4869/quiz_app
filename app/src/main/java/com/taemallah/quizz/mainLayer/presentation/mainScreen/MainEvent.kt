package com.taemallah.quizz.mainLayer.presentation.mainScreen

sealed class MainEvent {
    data object StartSoloQuiz: MainEvent()
    data object StartMatchQuiz: MainEvent()
}