package com.axxesscodingchallenge.ui.search

import com.axxesscodingchallenge.api.AxxessCodingChallengeService
import com.axxesscodingchallenge.data.model.GenericResponse
import com.axxesscodingchallenge.data.model.SearchResponse

import com.axxesscodingchallenge.utils.UseCaseResult
/**
 * @author Ankit Chandel
 * @since 04/11/20
 * <h1>SearchRepository</h1>
 * <p>Repository layer to get data from API and return to viewmodel <p>
 * */
interface SearchRepository {
    suspend fun getSearchResults(query: String): UseCaseResult<SearchResponse>
}

class SearchRepositoryImpl(private val axxessCodingChallengeService: AxxessCodingChallengeService) :
    SearchRepository{
    override suspend fun getSearchResults(query: String): UseCaseResult<SearchResponse> {
        return try {
            val result = axxessCodingChallengeService
                .getSearchAsync(query).await()

            UseCaseResult.Success(result)
        } catch (e: Exception) {
            UseCaseResult.Exception(e)
        }
    }


}