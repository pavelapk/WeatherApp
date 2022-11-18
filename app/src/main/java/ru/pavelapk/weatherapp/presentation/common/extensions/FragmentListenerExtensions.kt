package ru.pavelapk.weatherapp.presentation.common.extensions

import androidx.fragment.app.Fragment

object FragmentListenerExtensions {
    fun <T> getFragmentListenerOrNull(fragment: Fragment, listenerClass: Class<T>): T? {
        val parentFragment = fragment.parentFragment
        val parentActivity = fragment.activity

        return when {
            listenerClass.isInstance(parentFragment) -> listenerClass.cast(parentFragment)
            listenerClass.isInstance(parentActivity) -> listenerClass.cast(parentActivity)
            else -> null
        }
    }

    fun <T> getFragmentListener(fragment: Fragment, listenerClass: Class<T>): T {
        return getFragmentListenerOrNull(fragment, listenerClass)
            ?: throw KotlinNullPointerException("${fragment.activity}" +
                " or ${fragment.parentFragment} must implement $listenerClass")
    }
}

inline fun <reified T> Fragment.getParentAsListenerOrNull() =
    FragmentListenerExtensions.getFragmentListenerOrNull(this, T::class.java)

inline fun <reified T> Fragment.getParentAsListener() =
    FragmentListenerExtensions.getFragmentListener(this, T::class.java)
