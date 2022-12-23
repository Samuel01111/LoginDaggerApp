package br.com.leumas.daggerApp.ui.di

import br.com.leumas.daggerApp.MainActivity
import br.com.leumas.daggerApp.ui.login.LoginFragment
import br.com.leumas.daggerApp.ui.profile.ProfileFragment
import br.com.leumas.daggerApp.ui.registration.choosecredentials.ChooseCredentialsFragment
import br.com.leumas.daggerApp.ui.registration.profiledata.ProfileDataFragment
import dagger.Subcomponent

//Subcomponent that injects every fragment inside of MainActivity
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }
    //to inject Main activity and all fragments
    fun inject(activity: MainActivity)
    fun inject(activity: LoginFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: ProfileDataFragment)
    fun inject(fragment: ChooseCredentialsFragment)
}