package com.gibran.characters.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gibran.characters.data.api.RickAndMortyApi
import com.gibran.characters.data.api.mapper.toDomain
import com.gibran.characters.domain.model.Character
import java.io.IOException
import javax.inject.Inject

class CharacterPagingSource @Inject constructor (
    private val api: RickAndMortyApi
) : PagingSource<Int, Character>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1
            val response = api.getCharacters(page)
            val characters = response.results

            val prevKey = if (page > 0) page - 1 else null
            val nextKey = if (response.info.next != null) page + 1 else null

            LoadResult.Page(
                data = characters.map { it.toDomain() },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }
}