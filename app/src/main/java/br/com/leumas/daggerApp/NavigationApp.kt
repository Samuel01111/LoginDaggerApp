package br.com.leumas.daggerApp

import android.app.Application
import br.com.leumas.daggerApp.di.ApplicationComponent
import br.com.leumas.daggerApp.di.DaggerApplicationComponent

//is needed because dagger need a instantiation in Application level
class NavigationApp: Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        //instanciate the graph dependencies to deploy all the modules dependencies to ViewModel and Fragments
        appComponent = DaggerApplicationComponent.factory().create(this)
    }
}
