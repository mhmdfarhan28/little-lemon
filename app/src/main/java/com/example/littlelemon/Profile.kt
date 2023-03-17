package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.Black
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.Transparent

@Composable
fun Profile(navController: NavController, sharedPref: SharedPreferences) {
    LittleLemonTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Transparent,
                    title = {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Little Lemon Logo",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(36.dp)
                        )},
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ) { PaddingValues ->
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.personal_information),
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp, horizontal = 10.dp)
                    )
                    Box{
                        Text(
                            text = "First name",
                            style = MaterialTheme.typography.body2,
                            color = Black,
                            modifier = Modifier.padding(10.dp)
                        )
                        OutlinedTextField(
                            value = sharedPref.getString("firstName", "")!!,
                            readOnly = true,
                            onValueChange = {},
                            textStyle = MaterialTheme.typography.caption,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, bottom = 10.dp, top = 32.dp, end = 10.dp)
                        )
                    }
                    Box{
                        Text(
                            text = "Last name",
                            style = MaterialTheme.typography.caption,
                            color = Black,
                            modifier = Modifier.padding(10.dp)
                        )
                        OutlinedTextField(
                            value = sharedPref.getString("lastName", "")!!,
                            readOnly = true,
                            onValueChange = { },
                            textStyle = MaterialTheme.typography.caption,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, bottom = 10.dp, top = 32.dp, end = 10.dp)
                        )
                    }
                    Box {
                        Text(
                            text = "Email",
                            style = MaterialTheme.typography.caption,
                            color = Black,
                            modifier = Modifier.padding(10.dp)
                        )
                        OutlinedTextField(
                            value = sharedPref.getString("email", "")!!,
                            readOnly = true,
                            onValueChange = { },
                            textStyle = MaterialTheme.typography.caption,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, bottom = 10.dp, top = 32.dp, end = 10.dp)
                        )}

                    Button(
                        onClick = {
                            sharedPref.edit().clear().apply()
                            navController.navigate(OnBoarding.route)
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth()
                            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                    ) {
                        Text(
                            text = "Logout",
                            color = Black
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    LittleLemonTheme {
    }
}