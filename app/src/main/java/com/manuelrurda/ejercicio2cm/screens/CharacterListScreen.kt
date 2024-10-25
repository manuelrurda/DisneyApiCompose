package com.manuelrurda.ejercicio2cm.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelrurda.ejercicio2cm.R
import com.manuelrurda.ejercicio2cm.components.Background
import com.manuelrurda.ejercicio2cm.utils.transformDateFormat

@Composable
fun CharacterListScreen(){
    val charList = listOf(
        CharacterObject(
        "Sir Bart",
        "https://static.wikia.nocookie.net/disney/images/6/6d/Sword-in-stone-disneyscreencaps.com-8698.jpg",
        "2021-04-12T01:35:31.366Z"),
        CharacterObject(
            "Sir Bart",
            "https://static.wikia.nocookie.net/disney/images/6/6d/Sword-in-stone-disneyscreencaps.com-8698.jpg",
            "2021-04-12T01:35:31.366Z"))

    Background {
        CharacterListColumn(data = charList)
    }
}

@Composable
fun CharacterListColumn(data: List<CharacterObject>){
    LazyColumn {
        items(data){
            CharacterListItem(it)
        }
    }
}

@Composable
fun CharacterListItem(characterObject: CharacterObject) {
    ElevatedCard(
        modifier = Modifier.padding(all = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)){
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(8.dp)) {
            Image(
                painter = painterResource(R.drawable.img_placeholder_character),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = characterObject.name)
                Text(text = transformDateFormat(characterObject.dateCreated))
            }
        }
    }
}

data class CharacterObject(
    val name:String,
    val imageURL:String,
    val dateCreated:String
)

@Preview(showBackground = true)
@Composable
fun CharacterListScreenPreview(){
    CharacterListScreen()
}