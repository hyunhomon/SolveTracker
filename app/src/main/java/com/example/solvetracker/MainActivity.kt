package com.example.solvetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.solvetracker.data.PreferencesManager
import com.example.solvetracker.ui.component.BottomNavigationBar
import com.example.solvetracker.ui.screen.AnalysisScreen
import com.example.solvetracker.ui.screen.HomeScreen
import com.example.solvetracker.ui.screen.ProfileScreen
import com.example.solvetracker.ui.screen.SetUserHandleScreen
import com.example.solvetracker.ui.theme.SolveTrackerTheme
import com.example.solvetracker.ui.viewmodel.SearchUserViewModel

class MainActivity : ComponentActivity() {
    private val preferencesManager: PreferencesManager by lazy {
        PreferencesManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()
            val visibleScreens = setOf(Router.HomeScreen, Router.AnalysisScreen, Router.ProfileScreen)
            val currentRoute = navHostController.currentBackStackEntryAsState().value?.destination?.route

            SolveTrackerTheme(
                darkTheme = when(preferencesManager.getTheme()) {
                    1 -> false
                    2 -> true
                    else -> isSystemInDarkTheme()
                },
                bottomNavigation = currentRoute in visibleScreens
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentRoute in visibleScreens) {
                            BottomNavigationBar(navController = navHostController)
                        }
                    },
                    content = { innerPadding ->
                        Navigation(preferencesManager, navHostController, innerPadding)
                    }
                )
            }
        }
    }
}

@Composable
private fun Navigation(
    preferencesManager: PreferencesManager,
    navHostController: NavHostController,
    innerPadding: PaddingValues
) {
    val searchUserViewModel: SearchUserViewModel = viewModel()

    NavHost(
        navController = navHostController,
        startDestination = if (preferencesManager.getUserHandle().isEmpty()) Router.SetUserHandleScreen
        else Router.HomeScreen,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .navigationBarsPadding()
    ) {
        composable(Router.HomeScreen) { HomeScreen(navHostController) }
        composable(Router.AnalysisScreen) { AnalysisScreen(navHostController) }
        composable(Router.ProfileScreen) { ProfileScreen(navHostController) }
        composable(Router.SetUserHandleScreen) { SetUserHandleScreen(navHostController, preferencesManager, searchUserViewModel) }
    }
}