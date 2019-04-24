package xyz.kalapuneet.foundation.base

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.annotation.CallSuper

abstract class BaseFullScreenActivity: BaseActivity() {
    protected abstract val layoutId: Int

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(layoutId)
        setupModelAndUi()
    }

    protected abstract fun setupModelAndUi()
}