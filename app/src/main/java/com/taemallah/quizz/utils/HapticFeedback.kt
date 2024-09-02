package com.taemallah.quizz.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.core.content.ContextCompat

class HapticFeedback(private val context: Context) {
    fun triggerHapticFeedback(duration: Long = LONG_EFFECT) {
        Log.e("kid_e","trigger vibration")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val vibrator = ContextCompat.getSystemService(context,Vibrator::class.java)
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator?.cancel()
            vibrator?.let {
                if (it.hasVibrator()){
                    val vibrationEffect = VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)
                    it.vibrate(vibrationEffect)
                }
            }
        }
    }

    companion object{
        const val LONG_EFFECT : Long = 1000
        const val SHORT_EFFECT : Long = 500
    }

}