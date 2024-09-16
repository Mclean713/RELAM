package com.example.relam2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.relam2.ui.theme.Relam2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HeaderFooter()
        }
    }
}

@Composable
fun HeaderFooter(){
    var AlbumList by remember {
        mutableStateOf(true)
    }
    var TrendingList by remember {
        mutableStateOf(false)
    }
    val listOFAlbums = listOf<Album>(
        Album(
            title = "Subzero Baby",
            image = R.drawable.album
        )
    )
    val listOFSongs = listOf<Song>(
        Song(
            title = "Lil Baby-Pure Cocan",
            image = R.drawable.music_note
        )
    )
    Scaffold(
        topBar = {
            NavigationBar {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ){

                    Icon(imageVector = Icons.Outlined.Notifications, contentDescription = null)
                    Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
                }
            }
        },
        bottomBar = {
            NavigationBar {
                Row (
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = null,
                        modifier = Modifier.padding(horizontal =30.dp)
                    )
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier.padding(horizontal =30.dp)
                    )
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.padding(horizontal =30.dp)
                    )
                }
            }
        }

    ) {innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)

        ){
            Row (
                modifier = Modifier.fillMaxWidth()

            ){
                Button(
                    onClick = {
                        TrendingList = if(TrendingList == true) !TrendingList else TrendingList
                        AlbumList = !AlbumList
                    },
                    shape = RectangleShape,
                    modifier = Modifier.padding(horizontal = 2.dp)
                ) {
                    Text(text = "Albums")
                }

                Button(
                    onClick = {
                        TrendingList = !TrendingList
                        AlbumList = if(AlbumList == true) !AlbumList else AlbumList
                    },
                    shape = RectangleShape
                ) {
                    Text(text = "Trending Songs")
                }
            }
            AnimatedVisibility(
                visible = AlbumList
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ){
                    Column {
                        Text(
                            text = "Recent Albums",
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        )
                        LazyColumn {
                            items(listOFAlbums){item ->

                                OutlinedCard(
                                    shape = RectangleShape,
                                    modifier = Modifier
                                        .height(70.dp)
                                        .fillMaxWidth()
                                ) {
                                    Row {
                                        Image(
                                            painter = painterResource(id = item.image),
                                            contentDescription = null,
                                            modifier = Modifier.size(70.dp)
                                        )
                                        Text(
                                            text = "${item.title}",
                                            fontSize = 17.sp,
                                            modifier = Modifier
                                                .padding(vertical = 25.dp)
                                                .padding(horizontal = 20.dp)
                                        )

                                        Row (
                                            horizontalArrangement = Arrangement.End,
                                            modifier = Modifier.fillMaxWidth()
                                        ){
                                            Icon(
                                                imageVector = Icons.Filled.MoreVert,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .padding(vertical = 25.dp)
                                            )
                                        }
                                    }
                                }


                            }
                        }
                    }
                }
            }
            AnimatedVisibility(
                visible = TrendingList
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ){
                    Column {
                        Text(
                            text = "Trending Songs",
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        )
                        LazyColumn {
                            items(listOFSongs) { item ->
                                OutlinedCard(
                                    shape = RectangleShape,
                                    modifier = Modifier
                                        .height(70.dp)
                                        .fillMaxWidth()
                                ) {
                                    Row (
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ){
                                        Image(
                                            painter = painterResource(id = item.image),
                                            contentDescription = null,
                                            modifier = Modifier.size(30.dp)
                                        )
                                        Text(
                                            text = "${item.title}",
                                            fontSize = 17.sp,
                                            modifier = Modifier
                                                .padding(vertical = 25.dp)
                                                .padding(horizontal = 20.dp)
                                        )

                                        Row(
                                            horizontalArrangement = Arrangement.End,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.MoreVert,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .padding(vertical = 25.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }


        }
    }
}
data class Album(
    val title: String,
    val image:Int
)
data class Song(
    val title: String,
    val image:Int
)


