package br.com.leumas.daggerApp.di

import android.content.Context
import br.com.leumas.daggerApp.ui.di.MainComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

//@BindsInstance para poder passar um this(context) e o ele ser injetado.

@Singleton
@Component(modules = [
    DataModule::class,
    ViewModelBuilderModule::class,
    SubcomponentsModule::class,
    StringModule::class
])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
    //function to injection all activitys and fragment that are inside of this Component
    fun mainComponent(): MainComponent.Factory

}

@Module(subcomponents = [MainComponent::class])
abstract class SubcomponentsModule