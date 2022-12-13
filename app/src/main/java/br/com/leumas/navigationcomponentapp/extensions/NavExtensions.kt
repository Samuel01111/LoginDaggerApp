package br.com.leumas.navigationcomponentapp.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import br.com.leumas.navigationcomponentapp.R

private val navOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
    .build()

fun NavController.navigateWithAnimations(destinationIdRes: Int) {
    this.navigate(destinationIdRes, null, navOptions)
}

fun NavController.navigateWithAnimations(directions: NavDirections) {
    this.navigate(directions, navOptions)
}
