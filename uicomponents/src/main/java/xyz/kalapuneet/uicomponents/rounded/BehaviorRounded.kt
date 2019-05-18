package xyz.kalapuneet.uicomponents.rounded

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import xyz.kalapuneet.uicomponents.defaults.DefaultAttributeMethods
import xyz.kalapuneet.uicomponents.defaults.UiConstants
import kotlin.math.roundToInt

class BehaviorRounded(private val view: View): DefaultAttributeMethods {

    private val drawable = GradientDrawable()

    private var cornerRadius: Float = UiConstants.NO_VALUE
    private var topLeftRadius: Float = UiConstants.NO_VALUE
    private var topRightRadius: Float = UiConstants.NO_VALUE
    private var bottomLeftRadius: Float = UiConstants.NO_VALUE
    private var bottomRightRadius: Float = UiConstants.NO_VALUE

    private var solidColor: Int = UiConstants.NO_COLOR

    private var startColor: Int = UiConstants.NO_COLOR
    private var centerColor: Int = UiConstants.NO_COLOR
    private var endColor: Int = UiConstants.NO_COLOR

    private var strokeColor: Int = UiConstants.NO_COLOR
    private var strokeSize: Float = UiConstants.NO_VALUE
    private var dashWidth: Float = UiConstants.NO_VALUE
    private var dashGap: Float = UiConstants.NO_VALUE

    private lateinit var context: Context

    private fun getTransparent(): Int {
        return ContextCompat.getColor(context, android.R.color.transparent)
    }

    private fun Int.isColor(): Boolean {
        return this != UiConstants.NO_COLOR
    }

    private fun Float.hasValue(): Boolean {
        return this != UiConstants.NO_VALUE
    }

    private fun handleColors() {
        when {
            solidColor.isColor() -> drawable.setColor(solidColor)
            startColor.isColor() && centerColor.isColor() && endColor.isColor() -> drawable.colors =
                intArrayOf(startColor, centerColor, endColor)
            startColor.isColor() && endColor.isColor() -> drawable.colors = intArrayOf(startColor, endColor)
            startColor.isColor() && centerColor.isColor() -> drawable.colors =
                intArrayOf(startColor, centerColor, getTransparent())
            centerColor.isColor() && endColor.isColor() -> drawable.colors =
                intArrayOf(getTransparent(), centerColor, endColor)
            startColor.isColor() -> drawable.colors = intArrayOf(startColor, getTransparent())
            endColor.isColor() -> drawable.colors = intArrayOf(getTransparent(), endColor)
            centerColor.isColor() -> drawable.colors = intArrayOf(getTransparent(), centerColor, getTransparent())
            else -> drawable.setColor(getTransparent())
        }
    }

    private fun handleCornerRadius() {
        when {
            cornerRadius.hasValue() -> drawable.cornerRadius = cornerRadius
            topLeftRadius.hasValue() || topRightRadius.hasValue() || bottomLeftRadius.hasValue() || bottomRightRadius.hasValue() -> {
                if (topLeftRadius.hasValue().not()) {
                    topLeftRadius = 0f
                }
                if (topRightRadius.hasValue().not()) {
                    topRightRadius = 0f
                }
                if (bottomLeftRadius.hasValue().not()) {
                    bottomLeftRadius = 0f
                }
                if (bottomRightRadius.hasValue().not()) {
                    bottomRightRadius = 0f
                }
                drawable.cornerRadii = floatArrayOf(topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius)
            }
            else -> drawable.cornerRadius = 0f
        }
    }

    private fun handleStroke() {
        if (strokeColor.isColor() && strokeSize.hasValue()) {
            if (dashWidth.hasValue() && dashGap.hasValue()) {
                drawable.setStroke(strokeSize.roundToInt(), strokeColor, dashWidth, dashGap)
            } else {
                drawable.setStroke(strokeSize.roundToInt(), strokeColor)
            }
        }
    }

    private fun applyAttributes() {
        handleColors()
        handleCornerRadius()
        handleStroke()
        view.background = drawable
    }

    fun init(context: Context, attrs: AttributeSet?, set: RoundedStyleableSet) {
        this.context = context
        val typedArray = context.obtainStyledAttributes(attrs, set.base)
        typedArray.apply {
            setCornerRadius(dimension(set.cornerRadius))
            setCornerRadius(
                dimension(set.topLeftRadius),
                dimension(set.topRightRadius),
                dimension(set.bottomLeftRadius),
                dimension(set.bottomRightRadius)
            )
            setSolidColor(
                color(set.solidColor)
            )
            setGradientColors(
                color(set.startColor),
                color(set.centerColor),
                color(set.endColor)
            )
            setStrokeColor(
                color(set.strokeColor),
                dimension(set.strokeSize),
                dimension(set.dashWidth),
                dimension(set.dashGap)
            )
        }
        typedArray.recycle()
    }

    fun setCornerRadius(value: Float) {
        this.cornerRadius = value
        applyAttributes()
    }

    fun setCornerRadius(topLeft: Float, topRight: Float, bottomLeft: Float, bottomRight: Float) {
        this.topLeftRadius = topLeft
        this.topRightRadius = topRight
        this.bottomLeftRadius = bottomLeft
        this.bottomRightRadius = bottomRight
        applyAttributes()
    }

    fun setSolidColor(solidColor: Int) {
        this.solidColor = solidColor
        applyAttributes()
    }

    fun setGradientColors(startColor: Int, centerColor: Int, endColor: Int) {
        this.startColor = startColor
        this.centerColor = centerColor
        this.endColor = endColor
        applyAttributes()
    }

    fun setStrokeColor(color: Int, size: Float, dashWidth: Float, dashGap: Float) {
        this.strokeColor = color
        this.strokeSize = size
        this.dashWidth = dashWidth
        this.dashGap = dashGap
        applyAttributes()
    }
}