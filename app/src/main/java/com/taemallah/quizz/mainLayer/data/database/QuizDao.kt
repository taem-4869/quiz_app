package com.taemallah.quizz.mainLayer.data.database

import androidx.room.Dao
import androidx.room.Query
import com.taemallah.quizz.mainLayer.domain.module.Quiz
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {

    @Query("SELECT * FROM Quiz ORDER BY RANDOM()")
    fun getAll(): Flow<List<Quiz>>

    @Query("SELECT * FROM Quiz WHERE category like :category ORDER BY RANDOM()")
    fun getQuizByCategory(category: String): Flow<List<Quiz>>

}