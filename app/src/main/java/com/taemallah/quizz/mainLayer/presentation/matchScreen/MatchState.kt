package com.taemallah.quizz.mainLayer.presentation.matchScreen

import com.taemallah.quizz.mainLayer.domain.module.Quiz

data class MatchState(
    val quizList: List<Quiz> = emptyList(),
    val currentQuizIndex: Int = 0,
    val selectedOptionIndex: Int = 0,
    val timeoutProgress: Float = 0f,
    val blueStrikes: Int = 0,
    val redStrikes: Int = 0,
    private val maxStrikes: Int = 3,
    private val quizMaxTimeInSeconds: Int = 30,
){
    fun getCurrent(): Quiz{
        return quizList[currentQuizIndex]
    }

    fun getMaxStrikes(): Int{
        return maxStrikes
    }
    fun getQuizMaxTimeInSeconds(): Int{
        return quizMaxTimeInSeconds
    }
}
