package com.manuelrurda.ejercicio2cm.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.manuelrurda.ejercicio2cm.R
import com.manuelrurda.ejercicio2cm.components.Background
import com.manuelrurda.ejercicio2cm.models.CharacterModel
import com.manuelrurda.ejercicio2cm.utils.transformDateFormat
import com.manuelrurda.ejercicio2cm.viewmodels.CharactersViewModel

@Composable
fun CharacterListScreen(
    viewModel: CharactersViewModel,
    onCharacterClick: (Int) -> Unit
){
    val characters by viewModel.characters.collectAsState()

    Background {
        CharacterListColumn(data = characters, onCharacterClick)
    }
}

@Composable
fun CharacterListColumn(data: List<CharacterModel>, onCharacterClick: (Int) -> Unit){
    LazyColumn(modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 20.dp)) {
        items(data){
            CharacterListItem(it, onCharacterClick)
        }
    }
}

@Composable
fun CharacterListItem(characterModel: CharacterModel, onCharacterClick: (Int) -> Unit) {
    val hasImage = !characterModel.imageUrl.isNullOrEmpty()
    val avatar = rememberImagePainter(data = characterModel.imageUrl)
    ElevatedCard(
        modifier = Modifier
            .padding(all = 8.dp)
            .clickable {
                onCharacterClick(characterModel.id)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)){
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(8.dp)) {
            Image(
                painter = if (hasImage) avatar
                else painterResource(R.drawable.img_placeholder_character),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        shape = CircleShape,
                        color = Color.Black
                    )
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = characterModel.name)
                Text(text = transformDateFormat(characterModel.createdAt))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterListScreenPreview(){
//    CharacterListScreen(CharactersViewModel(),)
//    CharacterListItem(
//        characterModel = CharacterModel(
//            100,
//            "Sir Bart",
//            "https://static.wikia.nocookie.net/disney/images/6/6d/Sword-in-stone-disneyscreencaps.com-8698.jpg",
//            "2021-04-12T01:35:31.366Z"), navController = null
//    )
}