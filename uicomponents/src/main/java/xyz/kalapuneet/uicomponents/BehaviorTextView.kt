package xyz.kalapuneet.uicomponents

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.RequiresApi
import xyz.kalapuneet.uicomponents.defaults.DefaultViewMethods
import xyz.kalapuneet.uicomponents.rounded.BehaviorRounded
import xyz.kalapuneet.uicomponents.rounded.RoundedStyleableSet

class BehaviorTextView : TextView, DefaultViewMethods {
    
    val rounded: BehaviorRounded by lazy {
        BehaviorRounded(this)
    }

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        rounded.init(
            attrs,
            RoundedStyleableSet(
                base = R.styleable.BehaviorTextView,
                cornerRadius = R.styleable.BehaviorTextView_cornerRadius,
                topLeftRadius = R.styleable.BehaviorTextView_topLeftRadius,
                topRightRadius = R.styleable.BehaviorTextView_topRightRadius,
                bottomLeftRadius = R.styleable.BehaviorTextView_bottomLeftRadius,
                bottomRightRadius = R.styleable.BehaviorTextView_bottomRightRadius,
                solidColor = R.styleable.BehaviorTextView_solidColor,
                startColor = R.styleable.BehaviorTextView_startColor,
                centerColor = R.styleable.BehaviorTextView_centerColor,
                endColor = R.styleable.BehaviorTextView_endColor,
                strokeColor = R.styleable.BehaviorTextView_strokeColor,
                strokeSize = R.styleable.BehaviorTextView_strokeSize,
                dashWidth = R.styleable.BehaviorTextView_dashWidth,
                dashGap = R.styleable.BehaviorTextView_dashGap
            )
        )
    }
}