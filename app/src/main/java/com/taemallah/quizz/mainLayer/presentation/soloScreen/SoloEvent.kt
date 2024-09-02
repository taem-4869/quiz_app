package com.taemallah.quizz.mainLayer.presentation.soloScreen

import androidx.compose.ui.hapticfeedback.HapticFeedback

sealed class SoloEvent {
    data object FinishSolo: SoloEvent()
    data class SetSelectedOptionIndex(val index: Int) : SoloEvent()
    data class SubmitAnswer(val haptic: HapticFeedback) : SoloEvent()

    enum class QuizResult{
        Win, Lose, WithDraw
    }
}