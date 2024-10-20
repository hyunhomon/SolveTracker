package com.example.solvetracker.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.solvetracker.Router
import com.example.solvetracker.ui.theme.CustomIcons

data class ItemModel(
    val route: String,
    val icon: Int,
    val label: String
)

@Composable
fun BottomNavigationItem(item: ItemModel, isSelected: Boolean) {
    CustomIcon(
        res = item.icon,
        description = item.label,
        size = 24,
        color = if (isSelected) MaterialTheme.colorScheme.onBackground
        else MaterialTheme.colorScheme.onSurface
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = item.label,
        style = MaterialTheme.typography.labelMedium,
        color = if (isSelected) MaterialTheme.colorScheme.onBackground
        else MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val navigationItems = listOf(
        ItemModel(Router.HomeScreen, CustomIcons.Home, "홈"),
        ItemModel(Router.AnalysisScreen, CustomIcons.Analysis, "분석"),
        ItemModel(Router.ProfileScreen, CustomIcons.Profile, "프로필")
    )
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        navigationItems.forEach { item ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { if (item.route != currentRoute) navController.navigate(item.route) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BottomNavigationItem(
                    item = item,
                    isSelected = item.route == currentRoute
                )
            }
        }
    }
}