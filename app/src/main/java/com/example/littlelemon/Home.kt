package com.example.littlelemon

import androidx.activity.ComponentActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import com.bumptech.glide.integration.compose.GlideLazyListPreloader
import com.example.littlelemon.ui.theme.*
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Home(navController: NavController, items: List<MenuItemRoom>) {
    var searchPhrase by remember{
        mutableStateOf("")
    }
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
                                .padding(horizontal = 20.dp)
                                .height(36.dp)
                        )},
                    actions = {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .clickable { navController.navigate(Profile.route) }
                                .padding(10.dp)
                        )},
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ){ PaddingValues ->
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues)
            ){
                Box(
                    modifier = Modifier.background(color = Green)
                ) {
                    Text(
                        text = stringResource(id = R.string.restaurant_name),
                        style = MaterialTheme.typography.h1,
                        color = Yellow,
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, top = 75.dp, bottom = 20.dp)
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.65f)
                        ){
                            Text(
                                text = stringResource(id = R.string.city),
                                style = MaterialTheme.typography.h2,
                                color = White
                            )
                            Text(
                                text = stringResource(id = R.string.short_description),
                                style = MaterialTheme.typography.subtitle1,
                                color = White
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.hero_image),
                            contentDescription = "Hero Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .padding(top = 20.dp)
                                .clip(RoundedCornerShape(15.dp))
                        )
                    }
                    TextField(
                        value = searchPhrase,
                        leadingIcon = {
                            Icon( imageVector = Icons.Default.Search,
                            contentDescription = "")
                        },
                        onValueChange = { searchPhrase = it },
                        textStyle =  MaterialTheme.typography.caption,
                        placeholder = { Text(text = "Enter search phrase") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp, start = 20.dp, top = 250.dp, end = 20.dp)
                            .clip(RoundedCornerShape(20))
                            .background(color = White)
                    )
                }
                MenuCategory(items = items, searchPhrase)
            }
        }
    }
}
@Composable
fun MenuCategory(items: List<MenuItemRoom>, searchPhrase: String){
    var filteredItems by remember{
        mutableStateOf(items)
    }
    var selectedIndex by remember{
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
        .padding(10.dp)
    ){
        Text(
            text = stringResource(id = R.string.order_category),
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .padding(10.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(
                items = items.map{it.category}.toSet().toList(),
                itemContent = { menuItem ->
                    val bgColor by animateColorAsState(if (menuItem == selectedIndex) Black else White)
                    val txtColor by animateColorAsState(if (menuItem == selectedIndex) White else Green)
                    Button(
                        onClick = {
                            if (selectedIndex != menuItem) {
                                filteredItems = items.filter{ it.category.contains(menuItem,ignoreCase=true)}
                                selectedIndex = menuItem
                            } else {
                                filteredItems = items
                                selectedIndex = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(bgColor),
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .clip(RoundedCornerShape(50))
                    ){
                        Text(
                            text = menuItem.replaceFirstChar { it.uppercase() },
                            color = txtColor,
                            style = MaterialTheme.typography.h4,
                        )
                    }
                }

            )
        }
        Divider(
            color = White,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
    if(searchPhrase.isNotEmpty()) {
        filteredItems = filteredItems.filter{ it.title.contains(searchPhrase,ignoreCase=true) }
    }
    MenuItems(items = filteredItems)

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(items: List<MenuItemRoom>){
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                        Text(
                            text = menuItem.title,
                            style = MaterialTheme.typography.h4,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                        Text(
                            text = menuItem.description,
                            style = MaterialTheme.typography.body1,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp,end = 20.dp)
                        )
                        Text(
                            text = "$"+"%.2f".format(menuItem.price),
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                    }
                    GlideImage(
                        // TODO("Fetch data using glideimage
                        model = menuItem.image,
                        contentDescription = menuItem.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .padding(vertical = 10.dp)
                    )
                }
                Divider(
                    color = White,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
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