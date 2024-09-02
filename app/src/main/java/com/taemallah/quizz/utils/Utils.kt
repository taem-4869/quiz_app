package com.taemallah.quizz.utils

import android.R.anim
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder


val QuizNaveOptionsBuilder : NavOptions.Builder = NavOptions.Builder()
    .setEnterAnim(anim.fade_in)
    .setExitAnim(anim.fade_out)
    .setPopEnterAnim(anim.fade_in)
    .setPopExitAnim(anim.fade_out)
    .setPopUpTo(route = Route.MainScreen, inclusive = false, saveState = false)