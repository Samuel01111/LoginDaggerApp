package br.com.leumas.daggerApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import br.com.leumas.daggerApp.ui.di.MainComponent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        //all fragments inside of MainComponent
        mainComponent = (applicationContext as NavigationApp)
            .appComponent
            .mainComponent()
            .create()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(myToolbar)

        // responsaveis pela navegação entre as telas.
        navController = findNavController(R.id.nav_host_fragment)
        //Faz o gerenciamento de quem vai estar mostrando dependendo do current Fragment.
        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    //onSupportNavigateUp = serve para respeitar o pilha do backstack do navController
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || return super.onSupportNavigateUp() // usando esse super pra voltar entre activitys tambem
    }
}
