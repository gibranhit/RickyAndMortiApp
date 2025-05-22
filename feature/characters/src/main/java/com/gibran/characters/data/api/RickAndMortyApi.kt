package com.gibran.characters.data.api

import com.gibran.characters.data.api.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("/api/character/")
    suspend fun getCharacters(@Query("page") page: Int = 1): CharacterResponse
}
