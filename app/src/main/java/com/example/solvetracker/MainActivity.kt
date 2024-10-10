package com.example.solvetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.solvetracker.ui.component.BottomNavigationBar
import com.example.solvetracker.ui.screen.AnalysisScreen
import com.example.solvetracker.ui.screen.HomeScreen
import com.example.solvetracker.ui.screen.ProfileScreen
import com.example.solvetracker.ui.screen.SetUserHandleScreen
import com.example.solvetracker.ui.theme.SolveTrackerTheme
import com.example.solvetracker.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val windowController = WindowInsetsControllerCompat(window, window.decorView)

        windowController.isAppearanceLightStatusBars = true
        windowController.isAppearanceLightNavigationBars = true

        window.statusBarColor = getColor(R.color.status_bar)
        window.navigationBarColor = getColor(R.color.navigation_bar)

        setContent {
            SolveTrackerTheme {
                val navController = rememberNavController()
                val startDestination = if (Provider.userHandle == "") Router.setUserHandleScreen else Router.homeScreen
                val visibleScreens = setOf(Router.homeScreen, Router.analysisScreen, Router.profileScreen)
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentRoute in visibleScreens)
                            BottomNavigationBar(navController = navController)
                    },
                    content = { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = startDestination,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                                .navigationBarsPadding(),
                        ) {
                            composable(Router.homeScreen) { HomeScreen(navController) }
                            composable(Router.analysisScreen) { AnalysisScreen(navController) }
                            composable(Router.profileScreen) { ProfileScreen(navController) }
                            composable(Router.setUserHandleScreen) { SetUserHandleScreen(navController) }
                        }
                    }
                )
            }
        }
    }
}
