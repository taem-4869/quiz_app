package com.taemallah.quizz.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.taemallah.quizz.mainLayer.presentation.mainScreen.MainViewModel
import com.taemallah.quizz.mainLayer.presentation.mainScreen.components.MainScreen
import com.taemallah.quizz.mainLayer.presentation.matchScreen.MatchViewModel
import com.taemallah.quizz.mainLayer.presentation.matchScreen.components.MatchScreen
import com.taemallah.quizz.mainLayer.presentation.soloScreen.SoloViewModel
import com.taemallah.quizz.mainLayer.presentation.soloScreen.components.SoloScreen
import com.taemallah.quizz.ui.theme.QuizzTheme
import com.taemallah.quizz.utils.Route
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController : NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizzTheme {
                navController = rememberNavController()
                NavHost(navController = navController, startDestination = Route.MainScreen){
                    composable<Route.MainScreen>{
                        val viewModel by viewModels<MainViewModel>()
                        viewModel.setNavController(navController)
                        MainScreen (onEvent = viewModel::onEvent)
                    }
                    composable<Route.SoloScreen>{
                        val viewModel by viewModels<SoloViewModel>()
                        viewModel.setNavController(navController)
                        val state by viewModel.state.collectAsState()
                        SoloScreen (state = state,onEvent = viewModel::onEvent)
                    }
                    composable<Route.MatchScreen>{
                        val viewModel by viewModels<MatchViewModel>()
                        viewModel.setNavController(navController)
                        val state by viewModel.state.collectAsState()
                        MatchScreen (state = state,onEvent = viewModel::onEvent)
                    }
                }
            }
        }
    }

}