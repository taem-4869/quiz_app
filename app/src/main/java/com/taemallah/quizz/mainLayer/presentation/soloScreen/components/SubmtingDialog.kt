package com.taemallah.quizz.mainLayer.presentation.soloScreen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
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
fun SubmittingDialog(modifier: Modifier = Modifier, state: SoloState) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false,
        )
    ) {
        OutlinedCard(
            modifier = modifier,
            border = CardDefaults.outlinedCardBorder().copy(width = 2.dp),
            shape = CircleShape
        ){
            Text(
                text = state.timeoutSubmitting.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(24.dp).animateContentSize()
            )
        }
    }
}

@Preview
@Composable
private fun SubmittingDialogPreview() {
    SubmittingDialog(state = SoloState())
}