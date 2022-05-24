package com.example.somoni.extensions.navigation

import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.somoni.R

fun Fragment.activityNavController() =
    requireActivity().findNavController(R.id.fragment_container_view)

fun Fragment.findTopNavNavController() : NavController{
    var topLevelHost = requireActivity().supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment?
    return topLevelHost?.navController ?: findNavController()
}
fun NavController.navigateSafely(@IdRes actionId: Int) {
    currentDestination?.getAction(actionId)?.let { navigate(actionId) }
}
fun NavController.navigateSafely(directions: NavDirections) {
    currentDestination?.getAction(directions.actionId)?.let { navigate(directions) }
}
fun Fragment.overrideOnBackPressed(onBackPressed: OnBackPressedCallback.() -> Unit) =
    requireActivity().onBackPressedDispatcher.addCallback(this){
        onBackPressed()}
