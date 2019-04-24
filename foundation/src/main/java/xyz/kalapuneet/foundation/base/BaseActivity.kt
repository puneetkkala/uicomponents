package xyz.kalapuneet.foundation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import xyz.kalapuneet.foundation.extensions.isVisible

abstract class BaseActivity : AppCompatActivity(), LifecycleOwner {

    abstract val screenName: String

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun addFragment(container: View, frag: BaseFragment) {
        if (!container.isVisible) {
            container.isVisible = true
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(container.id, frag, frag.screenName)
        transaction.commit()
    }

    fun removeFragment(container: View, frag: BaseFragment) {
        if (container.isVisible) {
            container.isVisible = false
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.remove(frag)
        transaction.commit()
    }
}