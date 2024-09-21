package com.example.relam2.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Composable
fun Albumcard(album: Album){

    val transition = rememberInfiniteTransition()
    val color by transition.animateColor(
        initialValue = Color.Gray,
        targetValue = Color.LightGray,
        animationSpec = infiniteRepeatable(
            animation = tween(6000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )


    OutlinedCard (
        modifier = Modifier.size(220.dp).padding(10.dp),
        colors = CardDefaults.cardColors(
            color
        )
    ) {
      Image(
          painter = painterResource(id = album.cover),
          contentDescription = null,
          contentScale = ContentScale.Crop,
          modifier = Modifier
              .fillMaxWidth()
              .height(150.dp)
              .padding(5.dp),
      )
        Text(
            text = LocalContext.current.getString(album.title),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
        Text(
            text = LocalContext.current.getString(album.artist),
            fontFamily = FontFamily.Cursive,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
    }

}
@Composable
fun DisplayCard(image: Int){

}

@Composable
fun LazyList(list : List<Album>){

    LazyColumn {
        items(list){ item ->
            Albumcard(item)
        }
    }

}
@Composable
fun listAlbums(){
    val ListOfAlbums = listOf(
        Album(
            title = R.string.subzero_baby,
            cover = R.drawable.subzero_baby2,
            artist = R.string.lil_baby
        ),
        Album(
            title = R.string.hope,
            cover = R.drawable.hope,
            artist = R.string.nf
        ),
        Album(
            title = R.string.uthingo_le,
            cover = R.drawable.uthingo_le,
            artist = R.string.nkosazana_daughter
        )
    )
    LazyList(ListOfAlbums)
}
