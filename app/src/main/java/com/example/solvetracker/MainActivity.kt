package com.example.solvetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowInsetsControllerCompat
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
    private val windowController by lazy {
        WindowInsetsControllerCompat(window, window.decorView)
    }
    private val preferencesManager: PreferencesManager by lazy {
        PreferencesManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSystemWindow()

        setContent {
            SolveTrackerTheme {
                val navHostController = rememberNavController()
                val visibleScreens = setOf(Router.homeScreen, Router.analysisScreen, Router.profileScreen)
                val currentRoute = navHostController.currentBackStackEntryAsState().value?.destination?.route

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

    private fun setSystemWindow() {
        windowController.isAppearanceLightStatusBars = true
        windowController.isAppearanceLightNavigationBars = true

        window.statusBarColor = getColor(R.color.status_bar)
        window.navigationBarColor = getColor(R.color.navigation_bar)
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
        startDestination = if (preferencesManager.getUserHandle().isEmpty()) Router.setUserHandleScreen
        else Router.homeScreen,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .navigationBarsPadding(),
    ) {
        composable(Router.homeScreen) { HomeScreen(navHostController) }
        composable(Router.analysisScreen) { AnalysisScreen(navHostController) }
        composable(Router.profileScreen) { ProfileScreen(navHostController) }
        composable(Router.setUserHandleScreen) { SetUserHandleScreen(preferencesManager, navHostController, searchUserViewModel) }
    }
}
