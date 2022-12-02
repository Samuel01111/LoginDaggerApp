package br.com.leumas.navigationcomponentapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.leumas.navigationcomponentapp.R

class LoginViewModel : ViewModel() {

    sealed class AuthenticationState {
        class InvalidAuthentication(val fields: List<Pair<String, Int>>) : AuthenticationState()
    }

    val authenticationStateEvent = MutableLiveData<AuthenticationState>()

    fun authentication(userName: String, password: String) {
        if (isValidateForm(userName, password)) {
            //Usuario está autenticado
        } else {

        }
    }

    private fun isValidateForm(userName: String, password: String): Boolean {
        val invalidFields = arrayListOf<Pair<String, Int>>()

        if (userName.isEmpty()) {
            invalidFields.add(INPUT_USERNAME)
        }

        if (password.isEmpty()) {
            invalidFields.add(INPUT_PASSWORD)
        }

         if (invalidFields.isNotEmpty()){
             authenticationStateEvent.value = AuthenticationState.InvalidAuthentication(invalidFields)
             return false
         }

        return true

    }

    companion object {
        val INPUT_USERNAME = "INPUT_USERNAME" to R.string.login_input_layout_error_invalid_username
        val INPUT_PASSWORD = "INPUT_PASSWORD" to R.string.login_input_layout_error_invalid_password
    }
}