package com.axxesscodingchallenge.api

import com.axxesscodingchallenge.data.model.GenericResponse
import com.axxesscodingchallenge.data.model.SearchResponse

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Ankit Chandel
 * @since 04/11/20
 * <h1>AxxessCodingChallengeService</h1>
 * <p>All the API end points needs to be added here.</p>
 * */
interface AxxessCodingChallengeService{

    @GET("3/gallery/search/1")
    fun getSearchAsync(
        @Query("q")
        query: String
    ): Deferred<SearchResponse>
}