package com.gibran.rickyandmortiapp.navigation

import com.gibran.characters.domain.model.Character
import kotlinx.serialization.Serializable

@Serializable
object CharacterDestination

@Serializable
data class CharacterDetailDestination(
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val type: String,
    val gender: String,
    val species: String,
    val origin: String,
    val episodeList: List<String>
) {
    fun toCharacter() = Character(id, name, image, status, type, gender, species, origin, episodeList)
}