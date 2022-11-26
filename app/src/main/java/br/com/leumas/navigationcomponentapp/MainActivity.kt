package br.com.leumas.navigationcomponentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.leumas.navigationcomponentapp.ui.profile.ProfileFragment
import br.com.leumas.navigationcomponentapp.ui.start.StartFragment

class MainActivity : AppCompatActivity(), StartFragment.OnButtonClicked {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.my_container, StartFragment.newInstance())
            .commit()
    }

    override fun buttonClicked() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.my_container, ProfileFragment.newInstance())
            .commit()
    }
}