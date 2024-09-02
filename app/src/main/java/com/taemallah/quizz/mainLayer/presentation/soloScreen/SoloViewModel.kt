package com.taemallah.quizz.mainLayer.presentation.soloScreen

import android.util.Log
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.taemallah.quizz.mainLayer.domain.module.Quiz
import com.taemallah.quizz.mainLayer.domain.repository.QuizRepo
import com.taemallah.quizz.utils.HapticFeedback
import com.taemallah.quizz.utils.QuizNaveOptionsBuilder
import com.taemallah.quizz.utils.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class SoloViewModel @Inject constructor(
    private val quizRepo: QuizRepo,
    private val haptic: HapticFeedback
): ViewModel() {
    private lateinit var navController: NavController
    private val submissionDelayInSeconds: Int = 2

    private var _quizList = MutableStateFlow(emptyList<Quiz>())
    private val _state = MutableStateFlow(SoloState())
    val state = combine(_quizList,_state){ quizList, state->
        state.copy(
            quizList = quizList
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), SoloState())

    init {
        viewModelScope.launch {
            try {
                quizRepo.getAll().collectLatest{list->
                    delay(300)
                    _quizList.update {
                        list
                    }
                    if (list.isNotEmpty() && state.value.quizState==QuizState.Loading){
                        _state.update {
                            it.copy(quizState = QuizState.Started)
                        }
                    }
                }
            }catch (e : Exception){
                Log.e("kid_e",e.message?:"")
            }
        }
    }

    fun onEvent(event: SoloEvent){
        when(event){
            is SoloEvent.SetSelectedOptionIndex -> {
                _state.update {
                    it.copy(
                        selectedOptionIndex = event.index
                    )
                }
            }
            is SoloEvent.SubmitAnswer -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            quizState = QuizState.SubmittingAnswer
                        )
                    }
                    if (!state.value.isCorrectAnswer()){
                        event.haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        _state.update {
                            it.copy(strikes = it.strikes-1)
                        }
                    }
                    submissionAfterDelay()
                }
            }
            SoloEvent.FinishSolo -> {
                navigate()
            }
        }
    }

    private fun navigate() {
        _state.update { SoloState() }
        navController.navigate(
            Route.MainScreen,
            QuizNaveOptionsBuilder.build()
        )
    }

    fun setNavController(navController: NavController){
        this.navController = navController
    }

    private suspend fun submissionAfterDelay(){
        for (i in submissionDelayInSeconds downTo 0){
            _state.update {
                it.copy(timeoutSubmitting = i)
            }
            delay(1000)
        }
        if (state.value.nextQuestion()==null || state.value.isTheFinalQuiz()) {
            _state.update {
                it.copy(quizState = QuizState.Win)
            }
        }
        else if (state.value.strikes<=0){
            _state.update {
                it.copy(quizState = QuizState.Lose)
            }
        }
        else{
            try {
                _state.update {
                    state.value.nextQuestion()?:throw IllegalArgumentException("null solo state value while requesting next question. last question number: ${state.value.currentQuizIndex+1}, questions list size ${state.value.quizList.size}")
                }
            }catch (e: Exception){
                Log.e("kid_e", e.message?:"null error from submittingAfterDelay()")
            }
        }
    }
}