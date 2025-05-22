package com.gibran.characters.domain.repository

import androidx.paging.PagingData
import com.gibran.characters.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<PagingData<Character>>
}
