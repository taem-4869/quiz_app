package com.taemallah.quizz.mainLayer.data.repository

import com.taemallah.quizz.mainLayer.data.database.QuizDao
import com.taemallah.quizz.mainLayer.domain.module.Quiz
import com.taemallah.quizz.mainLayer.domain.repository.QuizRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuizRepoImpl @Inject constructor (
    private val quizDao: QuizDao
): QuizRepo {

    override fun getAll(): Flow<List<Quiz>> {
        return quizDao.getAll()
    }

    override fun getQuizByCategory(category: String): Flow<List<Quiz>> {
        return quizDao.getQuizByCategory(category)
    }

}