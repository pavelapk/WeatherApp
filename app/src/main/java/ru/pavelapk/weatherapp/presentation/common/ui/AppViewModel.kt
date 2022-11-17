package ru.pavelapk.weatherapp.presentation.common.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class AppViewModel<A : Action> : ViewModel() {
    private val _event = MutableLiveData<Event<A>>()
    val event: LiveData<Event<A>> get() = _event

    protected fun postEvent(action: A) {
        _event.postValue(Event(action))
    }
}
