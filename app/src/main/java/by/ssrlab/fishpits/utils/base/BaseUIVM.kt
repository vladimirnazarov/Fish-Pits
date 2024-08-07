package by.ssrlab.fishpits.utils.base

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import by.ssrlab.fishpits.R

open class BaseUIVM: ViewModel() {

    private lateinit var navController: NavController

    fun defineNavController(view: View){
        navController = view.findNavController()
    }

    fun setNavController(navController: NavController){
        this.navController = navController
    }

    fun getNavController() = navController

    fun navigate(address: Int) {
        navigateTo(address)
    }

    private fun navigateTo(address: Int){
        navController.navigate(
            address,
            bundleOf(),
            navOptions {
                anim {
                    enter = R.anim.nav_slide_in_left
                    popEnter = R.anim.nav_slide_in_right
                    popExit = R.anim.nav_slide_out_right
                    exit = R.anim.nav_slide_out_left
                }
            })
    }
}