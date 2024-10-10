package com.example.solvetracker.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LocalIcon(res: Int, size: Int) {
    Image(
        painter = painterResource(id = res),
        contentDescription = "Local Icon",
        modifier = Modifier.size(size.dp),
        contentScale = ContentScale.Fit
    )
}