package ru.pavelapk.weatherapp.presentation.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.databinding.FragmentSearchBinding
import ru.pavelapk.weatherapp.presentation.common.extensions.getParentAsListener
import ru.pavelapk.weatherapp.presentation.common.extensions.toast
import ru.pavelapk.weatherapp.presentation.common.statusbar.StatusBarColorContract
import ru.pavelapk.weatherapp.presentation.common.utils.KeyboardUtils
import ru.pavelapk.weatherapp.presentation.search.adapter.SearchAdapter
import ru.pavelapk.weatherapp.presentation.search.model.SearchAction
import ru.pavelapk.weatherapp.presentation.search.model.SearchState

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel by viewModels<SearchViewModel>()
    private val fragmentListener by lazy { getParentAsListener<SearchFragmentListener>() }
    private val statusBarColorListener by lazy { getParentAsListener<StatusBarColorContract>() }

    private val searchAdapter = SearchAdapter {
        viewModel.onLocationClick(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservers()
    }

    private fun initView() = with(binding) {
        statusBarColorListener.changeStatusBarColor(
            colorRes = R.color.white,
            isLight = true
        )
        searchEditText.requestFocus()
        KeyboardUtils.showSoftKeyboard(searchEditText)

        searchEditText.doOnTextChanged { text, _, _, _ -> viewModel.onQueryChange(text.toString()) }

        searchTextInputLayout.setEndIconOnClickListener {
            viewModel.onMyLocationClick()
        }

        recycler.adapter = searchAdapter
    }

    private fun initObservers() = with(viewModel) {
        state.observe(viewLifecycleOwner) {
            handleState(it)
        }
        event.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { action ->
                when (action) {
                    is SearchAction.Error -> toast(action.messageId)
                    SearchAction.OpenWeatherScreen -> fragmentListener.goToWeather()
                    SearchAction.RequestDeviceLocation -> fragmentListener.requestDeviceLocation()
                }
            }
        }
    }

    private fun handleState(state: SearchState) {
        searchAdapter.submitList(state.results)
    }

    interface SearchFragmentListener {
        fun goToWeather()
        fun requestDeviceLocation()
    }

    companion object {
        val TAG: String = SearchFragment::class.java.simpleName

        fun newInstance() = SearchFragment()
    }
}