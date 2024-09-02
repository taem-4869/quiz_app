package com.taemallah.quizz.mainLayer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.taemallah.quizz.mainLayer.domain.module.Quiz

@Database(
    entities = [Quiz::class],
    version = 1
)
abstract class QuizDatabase: RoomDatabase() {
    abstract val dao : QuizDao
}