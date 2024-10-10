package com.example.solvetracker.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.solvetracker.Provider

@Composable
fun SetUserHandleScreen(navController: NavHostController) {
    val provider = LocalContext.current.applicationContext as Provider

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = {
                provider.setUserHandle("hyunhomon")
            }
        ) {
            Text("유저 핸들 설정")
        }
    }
}