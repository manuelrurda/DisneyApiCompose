package com.manuelrurda.ejercicio2cm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.manuelrurda.ejercicio2cm.screens.CharacterDetailsScreen
import com.manuelrurda.ejercicio2cm.screens.CharacterListScreen
import com.manuelrurda.ejercicio2cm.ui.theme.Ejercicio2CMTheme
import com.manuelrurda.ejercicio2cm.viewmodels.CharactersViewModel
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio2CMTheme {
                navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = CharacterList
                ){
                    composable<CharacterList> {
                        CharacterListScreen(viewModel) { id ->
                            navController.navigate(CharacterDetails(id))
                        }
                    }
                    composable<CharacterDetails> {
                        val args = it.toRoute<CharacterDetails>()
                        CharacterDetailsScreen(id = args.id, viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Serializable
object CharacterList

@Serializable
data class CharacterDetails(val id:Int)