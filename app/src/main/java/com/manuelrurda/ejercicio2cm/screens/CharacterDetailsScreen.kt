package com.manuelrurda.ejercicio2cm.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.manuelrurda.ejercicio2cm.ui.theme.CharacterHeadingTextStyle
import com.manuelrurda.ejercicio2cm.ui.theme.HeadingTextStyle
import com.manuelrurda.ejercicio2cm.ui.theme.HintGray
import com.manuelrurda.ejercicio2cm.ui.theme.SubHeadingTextStyle
import com.manuelrurda.ejercicio2cm.ui.theme.TransparentBG
import com.manuelrurda.ejercicio2cm.utils.transformDateFormat
import com.manuelrurda.ejercicio2cm.viewmodels.CharactersViewModel

@Composable
fun CharacterDetailsScreen(id:Int, viewModel: CharactersViewModel){

    val character by viewModel.character.collectAsState()

    LaunchedEffect(id) {
        viewModel.clearCharacter()
        viewModel.getCharacterById(id)
    }

    println(character.toString())
    val hasImage = !character.imageUrl.isNullOrEmpty()
    val avatar = rememberImagePainter(data = character.imageUrl)

    Background {
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            colors = CardDefaults.cardColors(containerColor = TransparentBG),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)) {
            Column(modifier = Modifier.padding(15.dp)) {
                Text(text = character.name,
                    style = CharacterHeadingTextStyle,
                    color = Color.White)
                Text(text = transformDateFormat(character.createdAt),
                    style = SubHeadingTextStyle,
                    color = HintGray)
                Spacer(modifier = Modifier.height(20.dp))
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
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(modifier = Modifier.weight(weight = 1f, fill = false)) {
                    if(character.films.isNotEmpty()){
                        item {
                            Text(text = stringResource(id = R.string.heading_films),
                                style = HeadingTextStyle,
                                color = Color.White)
                        }
                        items(character.films){
                            Text(text = "\u2022 $it",
                                style = SubHeadingTextStyle,
                                color = HintGray,
                                modifier = Modifier.padding(vertical = 5.dp, horizontal = 3.dp))
                        }
                        item{
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }

                    if(character.shortFilms.isNotEmpty()){
                        item {
                            Text(text = stringResource(id = R.string.heading_shorts),
                                style = HeadingTextStyle,
                                color = Color.White)
                        }
                        items(character.shortFilms){
                            Text(text = "\u2022 $it",
                                style = SubHeadingTextStyle,
                                color = HintGray,
                                modifier = Modifier.padding(vertical = 5.dp, horizontal = 3.dp))
                        }
                        item{
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }

                    if(character.tvShows.isNotEmpty()){
                        item {
                            Text(text = stringResource(id = R.string.heading_shows),
                                style = HeadingTextStyle,
                                color = Color.White)
                        }
                        items(character.tvShows){
                            Text(text = "\u2022 $it",
                                style = SubHeadingTextStyle,
                                color = HintGray,
                                modifier = Modifier.padding(vertical = 5.dp, horizontal = 3.dp))
                        }
                        item{
                            Spacer(modifier = Modifier.height(15.dp))
                        }
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