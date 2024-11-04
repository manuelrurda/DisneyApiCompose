package com.manuelrurda.ejercicio2cm.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.manuelrurda.ejercicio2cm.R
import com.manuelrurda.ejercicio2cm.components.Background
import com.manuelrurda.ejercicio2cm.models.CharacterModel
import com.manuelrurda.ejercicio2cm.ui.theme.HeadingTextStyle
import com.manuelrurda.ejercicio2cm.ui.theme.HintGray
import com.manuelrurda.ejercicio2cm.ui.theme.SubHeadingTextStyle
import com.manuelrurda.ejercicio2cm.ui.theme.TransparentBG
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
    LazyColumn(modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Image(
                painter = painterResource(R.drawable.img_disney_logo),
                contentDescription = stringResource(id = R.string.description_logo),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(100.dp)
                    .width(250.dp)
            )
        }
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
        colors = CardDefaults.cardColors(containerColor = TransparentBG),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Image(
                painter = if (hasImage) avatar
                else painterResource(R.drawable.img_placeholder_character),
                contentDescription = stringResource(id = R.string.description_avatar),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        shape = CircleShape,
                        color = Color.White
                    )
                    .background(color = Color.White)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = characterModel.name,
                    style = HeadingTextStyle,
                    color = Color.White)
                Text(text = transformDateFormat(characterModel.createdAt),
                    style = SubHeadingTextStyle,
                    color = HintGray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterListScreenPreview(){
    CharacterListScreen(CharactersViewModel(),{})
}