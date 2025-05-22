package com.gibran.characters.data.api.mapper

import com.gibran.characters.data.api.response.CharacterDto
import com.gibran.characters.domain.model.Character


fun CharacterDto.toDomain(): Character {
    return Character(
        id = id,
        name = name,
        image = image,
        episodeList = episode,
        status = status,
        type = type,
        gender = gender,
        species = species,
        origin = origin.name
    )
}
