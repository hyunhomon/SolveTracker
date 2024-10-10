package com.example.solvetracker.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.solvetracker.Router
import com.example.solvetracker.ui.theme.AnalysisActivate
import com.example.solvetracker.ui.theme.AnalysisInactivate
import com.example.solvetracker.ui.theme.Gray3
import com.example.solvetracker.ui.theme.Gray5
import com.example.solvetracker.ui.theme.HomeActivate
import com.example.solvetracker.ui.theme.HomeInactivate
import com.example.solvetracker.ui.theme.ProfileActivate
import com.example.solvetracker.ui.theme.ProfileInactivate
import com.example.solvetracker.ui.theme.Typography
import com.example.solvetracker.ui.theme.White

data class ItemModel(
    val route: String,
    val activeIcon: Int,
    val inactiveIcon: Int,
    val label: String
)

@Composable
fun BottomNavigationItem(item: ItemModel, isSelected: Boolean) {
    LocalIcon(
        res = if (isSelected) item.activeIcon else item.inactiveIcon,
        size = 24
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = item.label,
        style = Typography.labelMedium,
        color = if (isSelected) Gray5 else Gray3
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val navigationItems = listOf(
        ItemModel(Router.homeScreen, HomeActivate, HomeInactivate, "홈"),
        ItemModel(Router.analysisScreen, AnalysisActivate, AnalysisInactivate, "분석"),
        ItemModel(Router.profileScreen, ProfileActivate, ProfileInactivate, "프로필")
    )
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        navigationItems.forEach { item ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { if (item.route != currentRoute) navController.navigate(item.route) },
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
