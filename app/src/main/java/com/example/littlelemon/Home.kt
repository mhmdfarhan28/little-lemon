package com.example.littlelemon

import androidx.activity.ComponentActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.*
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Home(navController: NavController, items: List<MenuItemRoom>) {
    var searchPhrase by remember{
        mutableStateOf("")
    }
    LittleLemonTheme {
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
            Button(
                onClick = {
                    navController.navigate(Profile.route)
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = "Profile",
                    color = Black
                )
            }
            Column(
                modifier = Modifier.background(color = Green)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.65f)
                            .padding(end = 15.dp)
                    ){
                        Text(
                            text = stringResource(id = R.string.restaurant_name),
                            fontSize = 36.sp,
                            color = Yellow
                        )
                        Text(
                            text = stringResource(id = R.string.city),
                            fontSize = 23.sp,
                            color = White
                        )
                        Text(
                            text = stringResource(id = R.string.short_description),
                            color = White
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Hero Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clip(RoundedCornerShape(15))
                    )
                }
                TextField(
                    value = searchPhrase,
                    leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
                    onValueChange = { searchPhrase = it},
                    placeholder = { Text(text = "Enter search phrase",) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
                        .clip(RoundedCornerShape(10))
                        .background(color = White)
                )
            }
            MenuCategory(items = items)
            if(searchPhrase.isNotEmpty()) {
                val filtered = items.filter{ it.title.contains(searchPhrase,ignoreCase=true) }
                MenuItems(items = filtered)
            }
            else{
                MenuItems(items = items)
            }
        }
    }
}
@Composable
fun MenuCategory(items: List<MenuItemRoom>){
    var selectedCategory by remember{
        mutableStateOf("")
    }
    var isSelected by remember { mutableStateOf(false) }
    val buttonColors by animateColorAsState(
        if (isSelected) White else Black
    )
    Column(
        modifier = Modifier
        .padding(10.dp)
    ){
        Text(
            text = stringResource(id = R.string.order_category),
            modifier = Modifier
                .padding(10.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .toggleable(value = isSelected, onValueChange= {

                })
        ) {
            items(
                items = items,
                itemContent = { menuItem ->
                    Button(
                        onClick = {
                            isSelected = !isSelected
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColors),
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        Text(
                            text = menuItem.category,
                            color = Green
                        )
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(items: List<MenuItemRoom>){
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Divider(
                    color = White, thickness = 1.dp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                        Text(text = menuItem.title)
                        Text(text = menuItem.description, maxLines = 2, overflow = TextOverflow.Ellipsis)
                        Text(text = "%.2f".format(menuItem.price))
                    }
                    Image(
                        // TODO("Fetch data using glideimage)
                        painter = painterResource(id = R.drawable.greek_salad),
                        contentDescription = "",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )
                }
            },

        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    LittleLemonTheme {
        Home(
            rememberNavController(),
            listOf(
                MenuItemRoom(1,"Greek salad","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur fermentum nibh purus, vel elementum arcu blandit quis. Cras enim nisl, vestibulum et porta non, faucibus mattis sapien.",10.0,"","Starters"),
                MenuItemRoom(2,"Bruschetta","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur fermentum nibh purus, vel elementum arcu blandit quis. Cras enim nisl, vestibulum et porta non, faucibus mattis sapien.",10.0,"","Mains")
            )
        )
    }
}