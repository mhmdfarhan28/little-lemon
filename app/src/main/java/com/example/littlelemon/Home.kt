package com.example.littlelemon

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.Black
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Home(navController: NavController) {
    Column(){
        Text(text = "Home")
        Button(
            onClick = {
                navController.navigate(Profile.route)
            },
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .padding(top = 30.dp, start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = "Profile",
                color = Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    LittleLemonTheme {
        
    }
}