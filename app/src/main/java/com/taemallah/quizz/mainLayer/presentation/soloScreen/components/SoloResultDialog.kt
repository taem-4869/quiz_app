package com.taemallah.quizz.mainLayer.presentation.soloScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.taemallah.quizz.R
import com.taemallah.quizz.mainLayer.presentation.soloScreen.QuizState
import com.taemallah.quizz.mainLayer.presentation.soloScreen.SoloEvent
import com.taemallah.quizz.mainLayer.presentation.soloScreen.SoloState

@Composable
fun SoloResulDialog(modifier: Modifier = Modifier, state: SoloState, onEvent: (SoloEvent)->Unit) {
    Dialog(
        onDismissRequest = { onEvent(SoloEvent.FinishSolo) }
    ) {
        OutlinedCard(
            modifier.fillMaxWidth()
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ){
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = if (state.quizState == QuizState.Lose) stringResource(R.string.you_lose) else stringResource(R.string.congratulation),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider()
                }
                StrikesLayout(state = state)
                val text = if (state.quizState == QuizState.Lose)
                    stringResource(
                        R.string.solo_loseing_result,
                        state.currentQuizIndex + 1,
                        state.getMaxStrikes() - state.strikes
                    )
                else
                    stringResource(
                        R.string.solo_winning_result,
                        state.currentQuizIndex + 1,
                        state.getMaxStrikes() - state.strikes
                    )
                Text(
                    text = stringResource(
                        R.string.among_answers_you_got,
                        state.currentQuizIndex + 1,
                        state.getMaxStrikes()-state.strikes
                    )+", you will do best next time",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun SoloResultDialogPreview() {
    SoloResulDialog(onEvent = {}, state = SoloState())
}