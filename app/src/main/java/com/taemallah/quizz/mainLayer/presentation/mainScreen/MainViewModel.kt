package com.taemallah.quizz.mainLayer.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.taemallah.quizz.utils.QuizNaveOptionsBuilder
import com.taemallah.quizz.utils.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
): ViewModel() {

    private lateinit var navController: NavController

    fun onEvent(event: MainEvent){
        when(event){
            MainEvent.StartMatchQuiz -> navigate(Route.MatchScreen)
            MainEvent.StartSoloQuiz -> navigate(Route.SoloScreen)
        }
    }

    fun setNavController(navController: NavController){
        this.navController = navController
    }

    private fun navigate(route:Route){
        navController.navigate(
            route,
            QuizNaveOptionsBuilder.build()
        )

    }

}