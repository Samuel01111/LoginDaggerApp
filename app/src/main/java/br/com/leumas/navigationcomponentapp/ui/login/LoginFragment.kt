package br.com.leumas.navigationcomponentapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.com.leumas.navigationcomponentapp.R
import br.com.leumas.navigationcomponentapp.extensions.dismissError
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true) //warning to the fragment that it has options to be clicked and "onOptionsItemSelected()" will works

        viewModel.authenticationStateEvent.observe(viewLifecycleOwner) {
            when (it) {
                is LoginViewModel.AuthenticationState.InvalidAuthentication -> {
                    val validationFields: Map<String, TextInputLayout> = initValidationFields()

                    it.fields.forEach { fieldError ->
                        validationFields[fieldError.first]?.error = getString(fieldError.second)
                    }
                }
                is LoginViewModel.AuthenticationState.Authenticated -> {
                    findNavController().popBackStack()
                }
            }
        }

        inputLoginUsername.addTextChangedListener {
            inputLayoutLoginUsername.dismissError()
        }

        inputLoginPassword.addTextChangedListener {
            inputLayoutLoginPassword.dismissError()
        }

        buttonLoginSignIn.setOnClickListener {
            val userName = inputLoginUsername.text.toString()
            val password = inputLoginPassword.text.toString()

            viewModel.authentication(userName, password)
        }

        buttonLoginSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationNavigation)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            cancelAuthentication()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        cancelAuthentication()
        return true
    }

    private fun cancelAuthentication() {
        viewModel.refuseAuthentication()
        findNavController().popBackStack(R.id.startFragment, false)
    }

    private fun initValidationFields() = mapOf(
        LoginViewModel.INPUT_USERNAME.first to inputLayoutLoginUsername,
        LoginViewModel.INPUT_PASSWORD.first to inputLayoutLoginPassword
    )
}
