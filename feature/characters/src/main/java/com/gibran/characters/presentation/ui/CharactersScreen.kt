package com.gibran.characters.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gibran.characters.presentation.viewmodel.CharactersViewModel
import com.gibran.characters.domain.model.Character
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.gibran.characters.R

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = hiltViewModel(),
    onClick: (Character) -> Unit
) {
    val characters = viewModel.characters.collectAsLazyPagingItems()

    Box(modifier = modifier.fillMaxSize()) {

        when {
            characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(64.dp), color = Color.White
                    )
                }
            }

            characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
                Text(text = stringResource(R.string.no_characters_message))
            }

            characters.loadState.hasError -> {
                val error = (characters.loadState.refresh as LoadState.Error).error
                ErrorScreen(
                    message = error.message ?: stringResource(R.string.error_generic),
                    onRetry = { characters.retry() }
                )
            }

            else -> {
                CharactersList(
                    characters = characters,
                    onClick = onClick
                )

                if (characters.loadState.append is LoadState.Loading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(64.dp), color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CharactersList(characters: LazyPagingItems<Character>, onClick: (Character) -> Unit = {}) {

    LazyColumn {
        items(characters.itemCount) {
            characters[it]?.let { characterModel ->
                ItemList(characterModel, onClick)
            }
        }
    }

}

@Composable
fun ItemList(
    character: Character,
    onClick: (Character) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .height(180.dp)
            .clickable { onClick(character) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = character.name,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleLarge
                )
                StatusBadge(status = character.status)
                Text(
                    text = stringResource(R.string.species_label, character.species),
                    style = MaterialTheme.typography.bodySmall
                )
                GenderBadge(gender = character.gender)
                Text(
                    text = stringResource(R.string.origin_label, character.origin),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun StatusBadge(status: String) {
    val (color, textRes) = when (status.lowercase()) {
        "alive" -> Color.Green to R.string.status_alive
        "dead" -> Color.Red to R.string.status_dead
        else -> Color.Gray to R.string.status_unknown
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color.copy(alpha = 0.1f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = stringResource(id = textRes), color = color, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun GenderBadge(gender: String) {
    val (color, labelRes) = when (gender.lowercase()) {
        "male" -> Color.Blue to R.string.gender_male
        "female" -> Color.Magenta to R.string.gender_female
        "genderless" -> Color.Gray to R.string.gender_genderless
        else -> Color.LightGray to R.string.gender_unknown
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color.copy(alpha = 0.1f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = stringResource(id = labelRes), color = color, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun ErrorScreen(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.error_title),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onRetry,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = stringResource(R.string.retry_button))
        }
    }
}