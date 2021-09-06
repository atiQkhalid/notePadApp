package com.example.mynotes.extensions
import android.os.Bundle
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mynotes.R

/**
 * Extension function to replace Fragment Safely from AppCompatActivity
 * @param fragment destination Fragment
 * @param tag as String default value is fragment.javaClass.name
 * @param containerViewId as frame layout id default value is R.id.container
 * @param enterAnimation as starting animation
 * @param exitAnimation ending animation
 * @param popEnterAnimation if any
 * @param popExitAnimation if any
 */
fun AppCompatActivity.replaceFragmentSafely(
    fragment: Fragment,
    tag: String = fragment.javaClass.name,
    allowStateLoss: Boolean = true,
    addToBackStack: Boolean = false,
    @IdRes containerViewId: Int = R.id.fragmentLayout,
    @AnimRes enterAnimation: Int = 0,
    @AnimRes exitAnimation: Int = 0,
    @AnimRes popEnterAnimation: Int = 0,
    @AnimRes popExitAnimation: Int = 0
) {
    val ft = supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        .replace(containerViewId, fragment, tag)

    if (addToBackStack) {
        ft.addToBackStack(null)
    }

    if (!supportFragmentManager.isStateSaved) {
        ft.commit()
    } else if (allowStateLoss) {
        ft.commitAllowingStateLoss()
    }
}

fun Fragment.replaceFragment(
    fragment: Fragment,
    addToBackStack: Boolean = true,
    bundle: Bundle? = null,
    shouldReplace: Boolean = true
) {
    val transaction =
        this.parentFragmentManager.beginTransaction()
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }
    if (shouldReplace)
        transaction.replace(R.id.fragmentLayout, fragment, fragment.javaClass.name)
    else
        transaction.add(R.id.fragmentLayout, fragment, fragment.javaClass.name)

    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    fragment.arguments = bundle
    transaction.commit()
}


fun AppCompatActivity.backPress() {
    val fragmentManager: FragmentManager = this.supportFragmentManager
    if (fragmentManager.backStackEntryCount >= 1) {
        fragmentManager.popBackStack()
    } else {
        this.finish()
    }
}