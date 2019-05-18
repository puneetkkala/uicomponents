package xyz.kalapuneet.uicomponents

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import xyz.kalapuneet.uicomponents.defaults.DefaultViewMethods
import xyz.kalapuneet.uicomponents.rounded.BehaviorRounded
import xyz.kalapuneet.uicomponents.rounded.RoundedStyleableSet

class BehaviorConstraintLayout : ConstraintLayout, DefaultViewMethods {

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

    private fun init(attrs: AttributeSet?) {
        rounded.init(
            context,
            attrs,
            RoundedStyleableSet(
                base = R.styleable.BehaviorConstraintLayout,
                cornerRadius = R.styleable.BehaviorConstraintLayout_cornerRadius,
                topLeftRadius = R.styleable.BehaviorConstraintLayout_topLeftRadius,
                topRightRadius = R.styleable.BehaviorConstraintLayout_topRightRadius,
                bottomLeftRadius = R.styleable.BehaviorConstraintLayout_bottomLeftRadius,
                bottomRightRadius = R.styleable.BehaviorConstraintLayout_bottomRightRadius,
                solidColor = R.styleable.BehaviorConstraintLayout_solidColor,
                startColor = R.styleable.BehaviorConstraintLayout_startColor,
                centerColor = R.styleable.BehaviorConstraintLayout_centerColor,
                endColor = R.styleable.BehaviorConstraintLayout_endColor,
                strokeColor = R.styleable.BehaviorConstraintLayout_strokeColor,
                strokeSize = R.styleable.BehaviorConstraintLayout_strokeSize,
                dashWidth = R.styleable.BehaviorConstraintLayout_dashWidth,
                dashGap = R.styleable.BehaviorConstraintLayout_dashGap
            )
        )
    }
}