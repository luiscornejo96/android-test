package com.luis.test.helper

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.luis.test.R


/**
 * @param view: View
 * @param message: String
 * Show a custom SnackBar for the Snackbar.LENGTH_LONG time
 */
fun Context.showCustomSnackBar(view: View, message: String)
{
    Snackbar.make( view, message, Snackbar.LENGTH_LONG)
        .setTextColor( ContextCompat.getColor( this, R.color.white))
        .setBackgroundTint( ContextCompat.getColor( this, R.color.accent))
        .show()
}

val Context.fadeIn: Animation get() = AnimationUtils.loadAnimation( this, R.anim.fade_in)
val Context.fadeOut: Animation get() = AnimationUtils.loadAnimation( this, R.anim.fade_out)