package br.com.leumas.daggerApp.ui.di

import androidx.lifecycle.ViewModel
import br.com.leumas.daggerApp.di.ViewModelKey
import br.com.leumas.daggerApp.ui.login.LoginViewModel
import br.com.leumas.daggerApp.ui.registration.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

//module to inject all of the screens
@Module
interface MainModule {

    @Binds
    @IntoMap
    //need this custom Annotation because the dagger would be lost to inject those ViewModel
    //because both of the function returns the same type (ViewModel).
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(viewModel: RegistrationViewModel): ViewModel
}