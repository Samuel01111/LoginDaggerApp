package br.com.leumas.navigationcomponentapp.extensions

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.dismissError() {
    error = null
    isErrorEnabled = false
}
