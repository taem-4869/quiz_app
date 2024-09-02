package com.taemallah.quizz.mainLayer.presentation.soloScreen.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taemallah.quizz.R
import com.taemallah.quizz.mainLayer.domain.module.Quiz
import com.taemallah.quizz.mainLayer.presentation.soloScreen.QuizState
import com.taemallah.quizz.mainLayer.presentation.soloScreen.SoloEvent
import com.taemallah.quizz.mainLayer.presentation.soloScreen.SoloState
import com.taemallah.quizz.utils.Constants

@Composable
fun SoloScreen(state: SoloState, onEvent: (SoloEvent) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {paddingValues ->
        val haptic = LocalHapticFeedback.current
        AnimatedContent(
            targetState = state.quizState,
            label = "",
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            when(it){
                QuizState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center){
                        CircularProgressIndicator()
                    }
                }
                QuizState.Win, QuizState.Lose -> {
                    SoloResulDialog(state = state, onEvent = onEvent)
                }
                QuizState.SubmittingAnswer, QuizState.Started -> {
                    if (state.quizState==QuizState.SubmittingAnswer) SubmittingDialog(state = state)
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(Constants.SCREEN_HORIZONTAL_PADDING),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            text = stringResource(R.string.solo_quiz),
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                        StrikesLayout(
                            modifier = Modifier
                                .fillMaxWidth(.5f)
                            ,state = state
                        )
                        CurrentQuizCard(modifier = Modifier, state = state, onEvent = onEvent)
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Button(
                                onClick = {
                                    onEvent(SoloEvent.SubmitAnswer(haptic))
                                },
                                enabled = state.quizState == QuizState.Started
                            ) {
                                Text(text = stringResource(R.string.next_question))
                            }
                            TextButton(
                                onClick = {
                                    onEvent(SoloEvent.FinishSolo)
                                },
                                enabled = state.quizState == QuizState.Started
                            ) {
                                Text(text = stringResource(R.string.withdraw))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun SoloScreenPreview() {
    SoloScreen(state = SoloState(
        listOf(
            Quiz(
                1,
                "why are you guy",
                "cuz it's a meme",
                "cuz you spell it wrong",
                "who said i'm gay",
                "no comment",
                2,
                "Memes"
            )
        ),
        strikes = 3,
        quizState = QuizState.Started
    )) {

    }
}