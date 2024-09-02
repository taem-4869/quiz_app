package com.taemallah.quizz.mainLayer.presentation.soloScreen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taemallah.quizz.mainLayer.domain.module.Quiz
import com.taemallah.quizz.mainLayer.presentation.soloScreen.QuizState
import com.taemallah.quizz.mainLayer.presentation.soloScreen.SoloEvent
import com.taemallah.quizz.mainLayer.presentation.soloScreen.SoloState

@Composable
fun CurrentQuizCard(modifier: Modifier = Modifier, state: SoloState, onEvent: (SoloEvent) -> Unit) {
    OutlinedCard(
        shape = RoundedCornerShape(20),
        border = CardDefaults.outlinedCardBorder().copy(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "${state.currentQuizIndex+1}. "+(state.quizList[state.currentQuizIndex].question?:"")+" :",
                style = MaterialTheme.typography.bodyLarge
            )
            val animProgress = animateFloatAsState(targetValue = state.timeoutProgress, label = "timeout animation")
            LinearProgressIndicator(
                progress = {animProgress.value},
                modifier = Modifier.fillMaxWidth()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                state.getCurrent().getOptions().forEachIndexed{index: Int, option: String ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val radioColor : Color
                        val selectedRadio : Boolean
                        val enabledRadio : Boolean = state.quizState == QuizState.Started
                        if (state.quizState==QuizState.SubmittingAnswer){
                            radioColor = if (index == state.getCurrent().getCorrectIndex()) Color.Green else{ Color.Red }
                            selectedRadio = (index == state.getCurrent().getCorrectIndex()) || (index == state.selectedOptionIndex)
                        }else{
                            radioColor = RadioButtonDefaults.colors().selectedColor
                            selectedRadio = (index == state.selectedOptionIndex)
                        }
                        RadioButton(
                            selected = selectedRadio,
                            onClick = { onEvent(SoloEvent.SetSelectedOptionIndex(index)) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = radioColor,
                                disabledSelectedColor = radioColor
                            ),
                            enabled = enabledRadio
                        )
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .clickable { onEvent(SoloEvent.SetSelectedOptionIndex(index)) }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CurrentQuizCardPreview() {
    CurrentQuizCard(state = SoloState(
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
        )
    )) {
        
    }
}