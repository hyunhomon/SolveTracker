package com.example.solvetracker.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.solvetracker.ui.theme.Back
import com.example.solvetracker.ui.theme.Gray1
import com.example.solvetracker.ui.theme.Gray3
import com.example.solvetracker.ui.theme.Typography
import com.example.solvetracker.ui.theme.White

@Composable
fun SearchBar(
    navController: NavController,
    backRoute: String,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 12.dp)
    ) {
        if (backRoute.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { navController.navigate(backRoute) }
            ) {
                LocalIcon(res = Back, size = 24)
            }
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = true,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray1, RoundedCornerShape(6.dp)),
            textStyle = Typography.titleMedium,
        ) { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp, 8.dp)
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hint,
                        style = Typography.titleMedium,
                        color = Gray3
                    )
                }
                innerTextField()
            }
        }
    }
}