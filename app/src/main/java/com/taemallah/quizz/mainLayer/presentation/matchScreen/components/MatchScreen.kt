package com.taemallah.quizz.mainLayer.presentation.matchScreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.taemallah.quizz.R
import com.taemallah.quizz.mainLayer.presentation.matchScreen.MatchEvent
import com.taemallah.quizz.mainLayer.presentation.matchScreen.MatchState

@Composable
fun MatchScreen(state : MatchState, onEvent: (MatchEvent)-> Unit) {
    Box(
       modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(R.string.match_quiz_coming_soon), modifier = Modifier, textAlign = TextAlign.Center)
    }
}

@Preview
@Composable
private fun MatchScreenPreview() {
    MatchScreen(state = MatchState(), onEvent = {})
}