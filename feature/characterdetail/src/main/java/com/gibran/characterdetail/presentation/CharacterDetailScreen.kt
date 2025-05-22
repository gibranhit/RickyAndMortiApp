package com.gibran.characterdetail.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.gibran.characters.domain.model.Character
import com.gibran.characters.presentation.ui.GenderBadge
import com.gibran.characters.presentation.ui.StatusBadge

@Composable
fun CharacterDetailScreen(character: Character) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = character.name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        Image(
            painter = rememberAsyncImagePainter(character.image),
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(240.dp)
                .clip(RoundedCornerShape(24.dp))
                .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(24.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                StatusBadge(status = character.status)
                DetailItem(label = "Species", value = character.species)
                GenderBadge(gender = character.gender)
                DetailItem(label = "Origin", value = character.origin)
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Text(
        text = "$label: $value",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}


