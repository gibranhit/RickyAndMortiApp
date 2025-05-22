package com.gibran.characters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.gibran.characters.domain.model.Character
import com.gibran.characters.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    val characters: Flow<PagingData<Character>> = repository
        .getCharacters()

}

data class CharactersUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
