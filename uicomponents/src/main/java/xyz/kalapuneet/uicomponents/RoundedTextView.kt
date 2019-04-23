package xyz.kalapuneet.uicomponents

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import xyz.kalapuneet.uicomponents.UiConstants.NO_COLOR
import xyz.kalapuneet.uicomponents.UiConstants.NO_VALUE
import kotlin.math.roundToInt

class RoundedTextView : TextView {

    private val drawable = GradientDrawable()

    private var cornerRadius: Float = NO_VALUE
    private var topLeftRadius: Float = NO_VALUE
    private var topRightRadius: Float = NO_VALUE
    private var bottomLeftRadius: Float = NO_VALUE
    private var bottomRightRadius: Float = NO_VALUE

    private var mSolidColor: Int = NO_COLOR

    private var startColor: Int = NO_COLOR
    private var centerColor: Int = NO_COLOR
    private var endColor: Int = NO_COLOR

    private var strokeColor: Int = NO_COLOR
    private var strokeSize: Float = NO_VALUE
    private var dashWidth: Float = NO_VALUE
    private var dashGap: Float = NO_VALUE

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(context, attrs)
    }

    private fun getTransparent(): Int {
        return ContextCompat.getColor(context, android.R.color.transparent)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundedTextView)
        cornerRadius = typedArray.getDimension(R.styleable.RoundedTextView_cornerRadius, NO_VALUE)
        topLeftRadius = typedArray.getDimension(R.styleable.RoundedTextView_topLeftRadius, NO_VALUE)
        topRightRadius = typedArray.getDimension(R.styleable.RoundedTextView_topRightRadius, NO_VALUE)
        bottomLeftRadius = typedArray.getDimension(R.styleable.RoundedTextView_bottomLeftRadius, NO_VALUE)
        bottomRightRadius = typedArray.getDimension(R.styleable.RoundedTextView_bottomRightRadius, NO_VALUE)
        mSolidColor = typedArray.getColor(R.styleable.RoundedTextView_solidColor, NO_COLOR)
        startColor = typedArray.getColor(R.styleable.RoundedTextView_startColor, NO_COLOR)
        centerColor = typedArray.getColor(R.styleable.RoundedTextView_centerColor, NO_COLOR)
        endColor = typedArray.getColor(R.styleable.RoundedTextView_endColor, NO_COLOR)
        strokeColor = typedArray.getColor(R.styleable.RoundedTextView_strokeColor, NO_COLOR)
        strokeSize = typedArray.getDimension(R.styleable.RoundedTextView_strokeSize, NO_VALUE)
        dashWidth = typedArray.getDimension(R.styleable.RoundedTextView_dashWidth, NO_VALUE)
        dashGap = typedArray.getDimension(R.styleable.RoundedTextView_dashGap, NO_VALUE)
        typedArray.recycle()
        applyAttributes()
    }

    private fun Int.isColor() : Boolean {
        return this != NO_COLOR
    }

    private fun Float.hasValue() : Boolean {
        return this != NO_VALUE
    }

    private fun handleColors() {
        when {
            mSolidColor.isColor() -> drawable.setColor(mSolidColor)
            startColor.isColor() && centerColor.isColor() && endColor.isColor() -> drawable.colors = intArrayOf(startColor, centerColor, endColor)
            startColor.isColor() && endColor.isColor() -> drawable.colors = intArrayOf(startColor, endColor)
            startColor.isColor() && centerColor.isColor() -> drawable.colors = intArrayOf(startColor, centerColor, getTransparent())
            centerColor.isColor() && endColor.isColor() -> drawable.colors = intArrayOf(getTransparent(), centerColor, endColor)
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
        this.background = drawable
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

    fun setSolidColor(color: Int) {
        this.mSolidColor = solidColor
        applyAttributes()
    }

    fun setGradientColors(startColor: Int, centerColor: Int, endColor: Int) {
        this.startColor = startColor
        this.centerColor = centerColor
        this.endColor = endColor
        applyAttributes()
    }

    fun setStrokeColor(color: Int, size: Float) {
        this.strokeColor = color
        this.strokeSize = size
        applyAttributes()
    }

    fun setStrokeColorWithDash(color: Int, size: Float, dashWidth: Float, dashGap: Float) {
        this.strokeColor = color
        this.strokeSize = size
        this.dashWidth = dashWidth
        this.dashGap = dashGap
        applyAttributes()
    }
}