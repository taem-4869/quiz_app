package com.taemallah.quizz.mainLayer.domain.repository

import com.taemallah.quizz.mainLayer.domain.module.Quiz
import kotlinx.coroutines.flow.Flow

interface QuizRepo {

    fun getAll():Flow<List<Quiz>>

    fun getQuizByCategory(category: String):Flow<List<Quiz>>

}