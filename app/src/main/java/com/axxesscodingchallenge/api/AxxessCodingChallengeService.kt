package com.axxesscodingchallenge.api

import com.axxesscodingchallenge.data.model.GenericResponse
import com.axxesscodingchallenge.data.model.SearchResponse

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface AxxessCodingChallengeService{

    @GET("3/gallery/search/1")
    fun getSearchAsync(
        @Query("q")
        query: String
    ): Deferred<SearchResponse>
}