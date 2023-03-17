package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun OnBoarding(navController: NavController, sharedPref: SharedPreferences) {
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
    var isEmpty by remember {
        mutableStateOf(true)
    }
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    LittleLemonTheme {
        Scaffold(
            scaffoldState = scaffoldState,
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
            LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues)
            ) {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(color = MaterialTheme.colors.secondary)
                    ) {
                        Text(
                            text = stringResource(id = R.string.greeting),
                            color = Color.White,
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier
                                .padding(start = 10.dp, top = 40.dp, bottom = 40.dp)
                        )
                    }
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
                                style = MaterialTheme.typography.caption,
                                color = Black,
                                modifier = Modifier.padding(10.dp)
                            )
                            OutlinedTextField(
                                value = firstName,
                                onValueChange = { firstName = it },
                                placeholder = { Text(text = "Tilly", color = Gray)},
                                textStyle = MaterialTheme.typography.caption,
                                isError = error && firstName.isBlank(),
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
                                value = lastName,
                                onValueChange = { lastName = it },
                                placeholder = { Text(text = "Doe", color = Gray)},
                                textStyle = MaterialTheme.typography.caption,
                                isError = error && lastName.isBlank(),
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
                                keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Email),
                                value = email,
                                onValueChange = { email = it },
                                placeholder = { Text(text = "tillydoe@example.com", color = Gray) },
                                textStyle = MaterialTheme.typography.caption,
                                isError = error && email.isBlank(),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, bottom = 10.dp, top = 32.dp, end = 10.dp)
                            )
                        }
                        Button(
                            onClick = {
                                isEmpty = firstName.isBlank() || lastName.isBlank() || email.isBlank()
                                coroutineScope.launch {
                                    if (isEmpty) {
                                        error = true
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = "Registration unsuccessful. Please enter all data.",
                                            actionLabel = "OK"
                                        )
                                    } else {
                                        error = false
                                        sharedPref.edit().apply {
                                            putString("firstName", firstName)
                                            putString("lastName", lastName)
                                            putString("email", email)
                                            putBoolean("login", true)
                                            apply()
                                        }
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = "Registration successful!",
                                            actionLabel = "OK"
                                        )
                                        navController.navigate(Home.route)
                                    }
                                }
                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .fillMaxWidth()
                                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                        ) {
                            Text(
                                text = "Register",
                                color = Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun OnBoardingPreview() {
    LittleLemonTheme {
    }
}
