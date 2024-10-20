package com.example.solvetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solvetracker.data.model.UserInfo
import com.example.solvetracker.data.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchUserViewModel: ViewModel() {
    private val repository = UserRepository()

    private val _searchQuery = MutableStateFlow("")

    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    val searchResults = MutableStateFlow<List<UserInfo>>(emptyList())
    val isLoading = MutableStateFlow(false)

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    init {
        viewModelScope.launch {
            _searchQuery
                .debounce(200)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    if (query.isBlank()) {
                        searchResults.value = emptyList()
                        emptyFlow()
                    } else {
                        isLoading.value = true
                        repository.searchUsers(query)
                    }
                }
                .collect { response ->
                    searchResults.value = if (response.isSuccessful) response.body()?.items?.take(8) ?: emptyList()
                    else emptyList()

                    isLoading.value = false
                }
        }
    }
}