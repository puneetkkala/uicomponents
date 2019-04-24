package xyz.kalapuneet.foundation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract val screenName: String

    abstract val layoutId: Int

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupModel()
        setupUi(view)
    }

    abstract fun setupModel()
    abstract fun setupUi(view: View)
}