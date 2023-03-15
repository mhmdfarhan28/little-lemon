package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.Black
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Profile(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 100.dp, vertical = 20.dp)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.secondary)
        ){
            Text(
                text = "Let's get to know you",
                color = Color.White,
                modifier = Modifier
                    .padding(30.dp)
            )
        }
        Column(modifier = Modifier
            .padding(10.dp)) {
            Text(
                text = "Personal information",modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
            OutlinedTextField(
                value = "firstName",
                onValueChange = { },
                label = { Text("First name")},
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            OutlinedTextField(
                value = "lastName",
                onValueChange = { },
                label = { Text("Last name")},
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            OutlinedTextField(
                value = "email",
                onValueChange = { },
                label = { Text(text = "Email")},
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Button(
                onClick = {
                    //TODO("Clear all preferences data")
                    navController.navigate(OnBoarding.route)
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 10.dp, end = 10.dp)
            ) {
                Text(
                    text = "Logout",
                    color = Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    LittleLemonTheme {
        Profile(rememberNavController())
    }
}