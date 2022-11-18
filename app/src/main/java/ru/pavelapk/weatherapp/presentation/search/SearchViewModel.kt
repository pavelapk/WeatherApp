package ru.pavelapk.weatherapp.presentation.search

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.domain.location.FindCitiesUseCase
import ru.pavelapk.weatherapp.domain.location.UpdateCurrentLocationUseCase
import ru.pavelapk.weatherapp.domain.location.model.Location
import ru.pavelapk.weatherapp.presentation.common.mappers.ErrorMappers.errorToStringRes
import ru.pavelapk.weatherapp.presentation.common.ui.StatefulViewModel
import ru.pavelapk.weatherapp.presentation.search.model.SearchAction
import ru.pavelapk.weatherapp.presentation.search.model.SearchState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val findCitiesUseCase: FindCitiesUseCase,
    private val updateCurrentLocationUseCase: UpdateCurrentLocationUseCase,
) : StatefulViewModel<SearchState, SearchAction>(defaultState = SearchState()) {


    private val queryFlow = MutableStateFlow("")

    init {
        initQueryFlowObserver()
    }

    fun onQueryChange(query: String) {
        queryFlow.value = query
    }

    fun onLocationClick(location: Location) {
        viewModelScope.launch {
            updateCurrentLocationUseCase(location).onSuccess {
                postEvent(SearchAction.OpenWeatherScreen)
            }.onFailure {
                postEvent(SearchAction.Error(R.string.unknown_error))
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun initQueryFlowObserver() {
        queryFlow.filterNotNull()
            .map { it.trim() }
            .debounce(500)
            .distinctUntilChanged()
            .onEach { requestQuery(it) }
            .launchIn(viewModelScope)
    }

    private suspend fun requestQuery(query: String) {
        if (query.length < 3) {
            updateState { copy(results = listOf()) }
            return
        }
        findCitiesUseCase(query)
            .onSuccess {
                updateState { copy(results = it) }
            }.onFailure {
                postEvent(SearchAction.Error(errorToStringRes(it)))
            }
    }
}
