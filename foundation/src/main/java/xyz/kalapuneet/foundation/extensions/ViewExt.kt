package xyz.kalapuneet.foundation.extensions

import android.view.View

var View.isVisible: Boolean
    get() {
        return visibility == View.VISIBLE
    }
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }