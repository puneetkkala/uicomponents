package xyz.kalapuneet.foundation.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes

inline fun<reified T: Activity> Activity.navigate() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

inline fun<reified T: Activity> Activity.navigate(extras: Bundle) {
    val intent = Intent(this, T::class.java)
    intent.putExtras(extras)
    startActivity(intent)
}

inline fun<reified T: Activity> Activity.navigateAndFinish() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
    finish()
}

inline fun <reified T: Activity> Activity.navigateAndFinish(extras: Bundle) {
    val intent = Intent(this, T::class.java)
    intent.putExtras(extras)
    startActivity(intent)
    finish()
}

infix fun TextView.loadWith(@StringRes stringRes: Int) {
    this.setText(stringRes)
}

infix fun View.onClick(listener: Function1<View, Unit>) {
    this.setOnClickListener(listener)
}

fun Activity.actionViewWithUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    startActivity(intent)
}

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.CENTER, 0, 0)
        show()
    }
}

fun Context.someErrorOccurred() {
    shortToast("Some error occurred, please try again later!")
}