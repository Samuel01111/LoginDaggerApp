package br.com.leumas.daggerApp.ui.registration.choosecredentials

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.leumas.daggerApp.MainActivity
import br.com.leumas.daggerApp.R
import br.com.leumas.daggerApp.extensions.dismissError
import br.com.leumas.daggerApp.ui.login.LoginViewModel
import br.com.leumas.daggerApp.ui.registration.RegistrationViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_choose_credentials.*
import javax.inject.Inject

class ChooseCredentialsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val loginViewModel by viewModels<LoginViewModel> { viewModelFactory }

    private val registrationViewModel by viewModels<RegistrationViewModel> { viewModelFactory }

    private val args: ChooseCredentialsFragmentArgs by navArgs()

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_credentials, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        textChooseCredentialsName.text = getString(R.string.choose_credentials_text_name, args.name)

        val invalidFields = initValidationFields()
        listenToRegistrationStateEvent(invalidFields)
        registerViewListeners()
        registerDeviceBackStack()
    }

    private fun initValidationFields() = mapOf(
        RegistrationViewModel.INPUT_USERNAME.first to inputLayoutChooseCredentialsUsername,
        RegistrationViewModel.INPUT_PASSWORD.first to inputLayoutChooseCredentialsPassword
    )

    private fun listenToRegistrationStateEvent(validationFields: Map<String, TextInputLayout>) {
        registrationViewModel.registrationStateEvent.observe(viewLifecycleOwner){ registrationState ->
            when (registrationState) {
                is RegistrationViewModel.RegistrationState.RegistrationCompleted -> {
                    val token = registrationViewModel.authToken
                    val username = inputChooseCredentialsUsername.text.toString()

                    loginViewModel.authenticateToken(token, username)
                    navController.popBackStack(R.id.profileFragment, false)
                }
                is RegistrationViewModel.RegistrationState.InvalidCredentials -> {
                    registrationState.fields.forEach { fieldError ->
                        validationFields[fieldError.first]?.error = getString(fieldError.second)
                    }
                }
            }
        }
    }

    private fun registerViewListeners() {
        buttonChooseCredentialsNext.setOnClickListener {
            val username = inputChooseCredentialsUsername.text.toString()
            val password = inputChooseCredentialsPassword.text.toString()

            registrationViewModel.createCredentials(username, password)
        }

        inputChooseCredentialsUsername.addTextChangedListener {
            inputLayoutChooseCredentialsUsername.dismissError()
        }

        inputChooseCredentialsPassword.addTextChangedListener {
            inputLayoutChooseCredentialsPassword.dismissError()
        }
    }

    private fun registerDeviceBackStack() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            cancelRegistration()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        cancelRegistration()
        return super.onOptionsItemSelected(item)
    }

    private fun cancelRegistration() {
        registrationViewModel.userCancelledRegistration()
        navController.popBackStack(R.id.loginFragment, false)
    }
}
