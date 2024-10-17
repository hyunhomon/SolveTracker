package com.example.solvetracker.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.solvetracker.Router
import com.example.solvetracker.data.PreferencesManager
import com.example.solvetracker.ui.component.SearchBar
import com.example.solvetracker.ui.theme.White
import com.example.solvetracker.ui.viewmodel.SearchUserViewModel

@Composable
fun SetUserHandleScreen(
    preferencesManager: PreferencesManager,
    navController: NavController,
    searchUserViewModel: SearchUserViewModel
) {
    val searchQuery = searchUserViewModel.searchQuery.collectAsState().value
    val searchResults = searchUserViewModel.searchResults.collectAsState().value
    val isLoading = searchUserViewModel.isLoading.collectAsState().value

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        SearchBar(
            navController = navController,
            backRoute = if (preferencesManager.getUserHandle().isEmpty()) ""
            else Router.profileScreen,
            hint = "사용자 핸들을 입력해주세요",
            value = searchQuery,
            onValueChange = { searchUserViewModel.updateSearchQuery(it) }
        )
        if (isLoading) {
            CircularProgressIndicator()
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(searchResults) { user ->
                Text(text = user.handle)
            }
        }
    }
}