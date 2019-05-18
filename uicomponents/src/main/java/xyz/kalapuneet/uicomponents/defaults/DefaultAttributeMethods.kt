package xyz.kalapuneet.uicomponents.defaults

import android.content.res.TypedArray
import androidx.annotation.StyleableRes

interface DefaultAttributeMethods {

    fun TypedArray.dimension(@StyleableRes styleableRes: Int): Float {
        return getDimension(styleableRes, UiConstants.NO_VALUE)
    }

    fun TypedArray.color(@StyleableRes styleableRes: Int): Int {
        return getColor(styleableRes, UiConstants.NO_COLOR)
    }
}