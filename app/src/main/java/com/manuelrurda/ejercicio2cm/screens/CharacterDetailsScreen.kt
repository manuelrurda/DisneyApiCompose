package com.manuelrurda.ejercicio2cm.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.manuelrurda.ejercicio2cm.R
import com.manuelrurda.ejercicio2cm.components.Background
import com.manuelrurda.ejercicio2cm.utils.transformDateFormat
import com.manuelrurda.ejercicio2cm.viewmodels.CharactersViewModel

@Composable
fun CharacterDetailsScreen(id:Int, viewModel: CharactersViewModel){

    val character by viewModel.character.collectAsState()

    LaunchedEffect(id) {
        viewModel.clearCharacter()
        viewModel.getCharacterById(id)
    }

    val hasImage = !character.imageUrl.isNullOrEmpty()
    val avatar = rememberImagePainter(data = character.imageUrl)

    Background {
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)) {
            Column(modifier = Modifier.padding(15.dp)) {
//                Text(text = character.name)
                Text(text = "Character")
//                Text(text = transformDateFormat(character.createdAt))
                Text(text = "21 de Diciembre de 2024")
                Image(
                    painter = if (hasImage) avatar
                    else painterResource(R.drawable.img_placeholder_character),
                    contentDescription = stringResource(id = R.string.description_avatar),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(color = Color.White)

                )
                if(character.films.isNotEmpty()){
                    Text(text = stringResource(id = R.string.heading_films))
                    for (film:String in character.films){
                        Text(text = film)
                    }
                }

                if(character.shortFilms.isNotEmpty()){
                    Text(text = stringResource(id = R.string.heading_shorts))
                    for (short:String in character.shortFilms){
                        Text(text = short)
                    }
                }

                if(character.tvShows.isNotEmpty()){
                    Text(text = stringResource(id = R.string.heading_shows))
                    for (show:String in character.tvShows){
                        Text(text = show)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCharacter() {
    CharacterDetailsScreen(id = 307, viewModel = CharactersViewModel())
    
}