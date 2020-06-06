package com.battagliandrea.beerappandroid.ui.utils

import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan

open class FontSpan(private val font: Typeface?) : MetricAffectingSpan() {

    companion object {
        const val WRONG_TYPEFACE = 0
    }

    override fun updateMeasureState(textPaint: TextPaint) = updateTypeface(textPaint)

    override fun updateDrawState(textPaint: TextPaint) = updateTypeface(textPaint)

    private fun updateTypeface(textPaint: TextPaint) {
        textPaint.apply {
            typeface = Typeface.create(font, getOldStyle(typeface))
        }
    }

    private fun getOldStyle(typeface: Typeface?) = typeface?.style ?: WRONG_TYPEFACE

}