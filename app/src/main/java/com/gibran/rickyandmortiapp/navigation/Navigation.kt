package com.gibran.rickyandmortiapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.gibran.characterdetail.presentation.CharacterDetailScreen
import com.gibran.characters.presentation.ui.CharactersScreen

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = CharacterDestination
    ) {
        composable<CharacterDestination> {
            CharactersScreen(
                onClick = { character ->
                    navController.navigate(
                        CharacterDetailDestination(
                            id = character.id,
                            name = character.name,
                            image = character.image,
                            status = character.status,
                            type = character.type,
                            gender = character.gender,
                            species = character.species,
                            origin = character.origin,
                            episodeList = character.episodeList
                        )
                    )
                },
            )
        }

        composable<CharacterDetailDestination> { backStackEntry ->
            val args = backStackEntry.toRoute<CharacterDetailDestination>()
            CharacterDetailScreen(
                args.toCharacter()
            )
        }

    }
}
