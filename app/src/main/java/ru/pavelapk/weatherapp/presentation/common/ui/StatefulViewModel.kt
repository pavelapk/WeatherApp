package ru.pavelapk.weatherapp.presentation.common.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class StatefulViewModel<S : State, A : Action>(defaultState: S) : AppViewModel<A>() {
    protected open val _state = MutableLiveData(defaultState)
    val state: LiveData<S> get() = _state

    protected fun updateState(reducer: S.() -> S) {
        val currentState = _state.value ?: return
        _state.postValue(currentState.reducer())
    }
}
