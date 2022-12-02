package br.com.leumas.navigationcomponentapp.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.leumas.navigationcomponentapp.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.authenticationStateEvent.observe(viewLifecycleOwner) {
            when (it) {
                is LoginViewModel.AuthenticationState.InvalidAuthentication -> {
                    Snackbar.make(
                        this@LoginFragment.view ?: View(this@LoginFragment as Context),
                        "Invalid Authentication please verify the fields",
                        Snackbar.LENGTH_SHORT
                    ).show()

                }
            }

        }

        buttonLoginSignIn.setOnClickListener {
            val userName = inputLoginUsername.text.toString()
            val password = inputLoginPassword.text.toString()

            viewModel.authentication(userName, password)
        }
    }
}
