package com.taemallah.quizz.mainLayer.domain.module

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quiz(
    @PrimaryKey
    val id: Int,
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctAnswerPosition: Int,
    val category: String
){
    fun getOptions(): List<String> {
        return listOf(
            option1,
            option2,
            option3,
            option4,
        )
    }

    fun getCorrectIndex(): Int {
        return correctAnswerPosition.minus(1)
    }
}
