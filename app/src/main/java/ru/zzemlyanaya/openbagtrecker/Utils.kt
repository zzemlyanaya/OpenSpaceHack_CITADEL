package ru.zzemlyanaya.openbagtrecker

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

interface IOnBack{
    fun onBackPressed(): Boolean
}

object Constants {
    const val USER = "user"
    const val WIZARD = 0
    const val PIRATE = 1
    const val ADMIN = 2

}

fun Boolean?.toInt(): Int = if (this != null && this) 1 else 0

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}