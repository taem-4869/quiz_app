package com.taemallah.quizz.mainLayer.presentation.matchScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.taemallah.quizz.mainLayer.domain.module.Quiz
import com.taemallah.quizz.mainLayer.domain.repository.QuizRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    quizRepo: QuizRepo
): ViewModel() {
    private var _quizList = MutableStateFlow(emptyList<Quiz>())
    private val _state = MutableStateFlow(MatchState())
    val state = combine(_quizList,_state){ quizList, state->
        state.copy(
            quizList = quizList
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MatchState())

    private lateinit var navController: NavController

    init {
        viewModelScope.launch {
            try {
                quizRepo.getAll().collect{list->
                    _quizList.update {
                        list
                    }
                }
            }catch (e : Exception){
                Log.e("kid_e",e.message?:"")
            }
        }
    }

    fun onEvent(event: MatchEvent){
        when(event){
            is MatchEvent.SetSelectedOptionIndex -> TODO()
            MatchEvent.StartMatchQuiz -> TODO()
            MatchEvent.StartSoloQuiz -> TODO()
            MatchEvent.SubmitAnswer -> TODO()
            MatchEvent.WithdrawMatch -> TODO()
        }
    }

    fun setNavController(navController: NavController){
        this.navController = navController
    }

}