package com.taemallah.quizz.mainLayer.presentation.matchScreen

sealed class MatchEvent {
    data object StartSoloQuiz: MatchEvent()
    data object StartMatchQuiz: MatchEvent()
    data object SubmitAnswer: MatchEvent()
    data object WithdrawMatch: MatchEvent()
    data class SetSelectedOptionIndex(val index: Int) : MatchEvent()
}