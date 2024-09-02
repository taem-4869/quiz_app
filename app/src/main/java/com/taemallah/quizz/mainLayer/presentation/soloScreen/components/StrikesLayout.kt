package com.taemallah.quizz.mainLayer.presentation.soloScreen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.taemallah.quizz.R
import com.taemallah.quizz.mainLayer.presentation.soloScreen.SoloState

@Composable
fun StrikesLayout(modifier: Modifier = Modifier, imageSize: Dp = 30.dp, state: SoloState) {
    Row(
        modifier = modifier.animateContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (i in 1..state.getMaxStrikes()){
            Image(
                modifier = Modifier
                    .size(imageSize),
                painter = painterResource(id = R.drawable.strik),
                contentDescription = "strike",
                colorFilter = if (i<=state.strikes) null else ColorFilter.tint(Color.Gray)
            )
        }
    }
}

@Preview
@Composable
private fun StrikesLayoutPreview() {
    StrikesLayout(state = SoloState(strikes = 3, maxStrikes = 3))
}