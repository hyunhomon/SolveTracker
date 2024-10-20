package com.example.solvetracker.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.solvetracker.Router
import com.example.solvetracker.data.PreferencesManager
import com.example.solvetracker.ui.component.CustomIcon
import com.example.solvetracker.ui.component.SearchBar
import com.example.solvetracker.ui.theme.Tier
import com.example.solvetracker.ui.viewmodel.SearchUserViewModel
import kotlinx.coroutines.delay

@Composable
fun SetUserHandleScreen(
    navController: NavController,
    preferencesManager: PreferencesManager,
    searchUserViewModel: SearchUserViewModel
) {
    val focusManager = LocalFocusManager.current
    val userHandleEntered = preferencesManager.getUserHandle().isNotEmpty()

    val searchQuery = searchUserViewModel.searchQuery.collectAsState().value
    val searchResults = searchUserViewModel.searchResults.collectAsState().value
    val isLoading = searchUserViewModel.isLoading.collectAsState().value
    val showSkeleton = remember { mutableStateOf(false) }

    LaunchedEffect(isLoading) {
        if (isLoading) {
            delay(2000)
            showSkeleton.value = true
        } else {
            showSkeleton.value = false
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { focusManager.clearFocus() }
    ) {
        if (!userHandleEntered) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 40.dp, bottom = 8.dp)
            ) {
                Text(text = "계정 연결하기", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "당신의 백준 핸들을 선택해주세요", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        SearchBar(
            backBtn = userHandleEntered,
            backBtnEvent = { navController.popBackStack() },
            hint = "사용자 핸들을 입력해주세요",
            value = searchQuery,
            onValueChange = { searchUserViewModel.updateSearchQuery(it) },
            clearValue = { searchUserViewModel.updateSearchQuery("") }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (showSkeleton.value) {
                items(5) {
                    SkeletonItem()
                }
            } else {
                items(searchResults) { user ->
                    UserItem(tier = user.tier, handle = user.handle) {
                        focusManager.clearFocus()
                        navController.navigate(Router.HomeScreen) {
                            popUpTo(Router.SetUserHandleScreen) { inclusive = false }
                            launchSingleTop = true
                        }
                        preferencesManager.setUserHandle(user.handle)
                    }
                }
            }
        }
    }
}

@Composable
fun UserItem(tier: Int, handle: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 12.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        CustomIcon(Tier[tier], "티어", 20)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = handle, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
    }
}

@Composable
fun SkeletonItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(MaterialTheme.colorScheme.surface, CircleShape)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.small)
        )
    }
}