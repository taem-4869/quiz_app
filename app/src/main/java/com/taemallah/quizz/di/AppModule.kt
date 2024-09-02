package com.taemallah.quizz.di

import android.app.Application
import androidx.room.Room
import com.taemallah.quizz.mainLayer.data.database.QuizDao
import com.taemallah.quizz.mainLayer.data.database.QuizDatabase
import com.taemallah.quizz.mainLayer.data.repository.QuizRepoImpl
import com.taemallah.quizz.mainLayer.domain.repository.QuizRepo
import com.taemallah.quizz.utils.Constants
import com.taemallah.quizz.utils.HapticFeedback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesQuizDao(application: Application): QuizDao{
        return Room.databaseBuilder(
            application.applicationContext,
            QuizDatabase::class.java,
            Constants.QUIZ_DATABASE_NAME)
            .createFromAsset("database/quiz.db")
            .build()
            .dao
    }

    @Provides
    @Singleton
    fun providesQuizRepo(quizDao: QuizDao):QuizRepo{
        return QuizRepoImpl(quizDao)
    }

    @Provides
    @Singleton
    fun providesHapticFeedback(application: Application):HapticFeedback{
        return HapticFeedback(application)
    }

}