package com.reda.withingstest.ui.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(): ViewModel() {

    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun onQueryUpdate(query: String){
        viewModelScope.launch{
            _searchQuery.value = query
        }
    }

    fun onCtaClick(navigateToImages: (String) -> Unit){
        viewModelScope.launch{
            if (searchQuery.value.isNotEmpty()){
                navigateToImages(searchQuery.value)
            }
        }
    }
}