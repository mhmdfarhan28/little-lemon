package com.example.littlelemon

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.littlelemon.ui.theme.Black
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun OnBoarding() {
    var firstName by remember{
        mutableStateOf("")
    }
    var lastName by remember{
        mutableStateOf("")
    }
    var email by remember{
        mutableStateOf("")
    }
    var error by remember{
        mutableStateOf(false)
    }
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
                value = firstName,
                onValueChange = { firstName = it},
                label = { Text("First name")},
                isError = error,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it},
                label = { Text("Last name")},
                isError = error,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it},
                label = { Text(text = "Email")},
                isError = error,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Button(
                onClick = {
                    error = firstName.isBlank() && lastName.isBlank() && email.isBlank()
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 10.dp, end = 10.dp)
            ) {
                Text(
                    text = "Register",
                    color = Black
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun OnBoardingPreview(){
    LittleLemonTheme {
        OnBoarding()
    }
}
