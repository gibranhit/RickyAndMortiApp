package com.gibran.characters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gibran.characters.data.api.RickAndMortyApi
import com.gibran.characters.data.paging.CharacterPagingSource
import com.gibran.characters.domain.model.Character
import com.gibran.characters.domain.repository.CharacterRepository
import com.gibran.core.di.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi,
) : CharacterRepository {

    companion object {
        const val MAX_ITEMS = 10
        const val PREFETCH_ITEMS = 5
    }

    override fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = MAX_ITEMS,
                prefetchDistance = PREFETCH_ITEMS
            ),
            pagingSourceFactory = { CharacterPagingSource(api) }
        ).flow
    }

}
