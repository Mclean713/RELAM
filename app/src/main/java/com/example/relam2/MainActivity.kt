package com.example.relam2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.InfiniteAnimationPolicy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.relam2.data.icons
import com.example.relam2.ui.components.Albumcard
import com.example.relam2.ui.components.listAlbums
import com.example.relam2.ui.components.listSong
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
    val transition = rememberInfiniteTransition()
    val color by transition.animateColor(
        initialValue = Color.Gray,
        targetValue = Color.LightGray,
        animationSpec = infiniteRepeatable(
            animation = tween(7000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Scaffold(
        topBar = {
            NavigationBar {
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ){
                    VectorImageTop(Icons.Filled.Notifications)
                }
            }
        },
        bottomBar = {
            NavigationBar {
                Row (
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ){
                    ListOfIcons()

                }
            }
        }

    ) {innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)

        ){
            Row (
                modifier = Modifier.fillMaxWidth().background(color)

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
                        .background(color)
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
                        listAlbums()
                    }
                }
            }
            AnimatedVisibility(
                visible = TrendingList
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color)
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
                        listSong()
                    }

                }
            }


        }
    }
}
@Composable
fun ListOfIcons(){
    val IconList = listOf(
        icons(
            label = R.string.home,
            icon = Icons.Filled.Home
        ),
        icons(
            label = R.string.favourite,
            icon = Icons.Filled.Favorite
        ),
        icons(
            label = R.string.search,
            icon = Icons.Filled.Search
        ),
        icons(
            label = R.string.acoount,
            icon = Icons.Filled.AccountCircle
        )

    )
    LazyRow {
        items(IconList){ item ->
            Column(
                modifier = Modifier.padding(horizontal = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,)
                Text(
                    text = LocalContext.current.getString(item.label),
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }

}

@Composable
fun VectorImageTop(icon: ImageVector){
    Icon(imageVector = icon, contentDescription = null)
}
@Composable
fun ImageVectorBottom(icon: ImageVector){
    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier.padding(horizontal =30.dp)
    )
}



