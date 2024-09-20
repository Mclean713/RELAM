package com.example.relam2.ui.components


import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.relam2.R
import com.example.relam2.data.Album
import com.example.relam2.data.Song

@Composable
fun SongCard(image: Int,title: Int,artist:Int){

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


    OutlinedCard (
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            color
        )
    ){
        Row{
            Card (
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(6.dp)
            ){
              Image(
                  painter = painterResource(id = image),
                  contentScale = ContentScale.Crop,
                  contentDescription = null,
                  modifier = Modifier.size(100.dp)
              )
            }
            Spacer(modifier = Modifier.width(40.dp))
            Column(
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                Text(
                    text = LocalContext.current.getString(title),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = LocalContext.current.getString(artist),
                    fontFamily = FontFamily.Cursive,
                    fontSize = 18.sp
                )
            }

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = null,
                    modifier = Modifier.padding(vertical = 30.dp)
                )
            }
        }
    }





}

@Composable
fun LazyListSong(list : List<Song>){

    LazyColumn {
        items(list){ item ->
            SongCard(image = item.artwork, title = item.title, artist = item.artist)
        }
    }

}
@Composable
fun listSong(){
    val ListOfSongs = listOf(
        Song(
            title = R.string.monkey_dance,
            artwork = R.drawable.monkey_dance,
            artist = R.string.tones_and_i
        ),
        Song(
            title = R.string.dior,
            artwork = R.drawable.dior,
            artist = R.string.pop_smoke
        ),
        Song(
            title =R.string.unstopable,
            artwork = R.drawable.sia_,
            artist = R.string.sia
        ),
        Song(
            title = R.string.bank_account,
            artwork = R.drawable.bank_account,
            artist = R.string._21_savage
        ),
        Song(
            title = R.string.rockstar,
            artwork = R.drawable.rockstar,
            artist = R.string._21_savage_ft_post_m
        )

    )
    LazyListSong(ListOfSongs)
}