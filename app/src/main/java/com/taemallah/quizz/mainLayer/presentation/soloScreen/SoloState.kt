package com.taemallah.quizz.mainLayer.presentation.soloScreen

import com.taemallah.quizz.mainLayer.domain.module.Quiz

data class SoloState(
    val quizList: List<Quiz> = emptyList(),
    private val maxStrikes: Int = 3,
    private val quizMaxTimeInSeconds: Int = 30,
    private val questionsNumber: Int = 19,
    val currentQuizIndex: Int = 0,
    val selectedOptionIndex: Int = 0,
    val timeoutProgress: Float = 1f,
    val timeoutSubmitting: Int = 5,
    val strikes: Int = maxStrikes,
    val quizState: QuizState = QuizState.Loading,
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
    fun isCorrectAnswer():Boolean{
        return (getCurrent().correctAnswerPosition?.minus(1))==selectedOptionIndex
    }
    fun nextQuestion(): SoloState? {
        return if ((currentQuizIndex+1)<quizList.size){
            this.copy(
                currentQuizIndex = currentQuizIndex+1,
                selectedOptionIndex = 0,
                timeoutProgress = 1f,
                timeoutSubmitting = 5,
                quizState = QuizState.Started
            )
        }else{
            null
        }
    }
    fun isTheFinalQuiz(): Boolean {
        return currentQuizIndex>=questionsNumber
    }
}
