package xyz.kalapuneet.uicomponents.defaults

import android.view.View

interface DefaultViewMethods {

    fun View.onClick(body: () -> Unit) {
        setOnClickListener {
            body.invoke()
        }
    }
}