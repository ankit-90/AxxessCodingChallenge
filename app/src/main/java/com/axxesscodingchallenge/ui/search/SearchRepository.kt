package com.axxesscodingchallenge.ui.search

import com.axxesscodingchallenge.api.AxxessCodingChallengeService
import com.axxesscodingchallenge.data.model.GenericResponse
import com.axxesscodingchallenge.utils.UseCaseResult

interface SearchRepository {
    suspend fun getSearchResults(query: String): UseCaseResult<GenericResponse<User>>
}

class SearchRepositoryImpl(private val axxessCodingChallengeService: AxxessCodingChallengeService) :
    SearchRepository{
    override suspend fun getSearchResults(query: String): UseCaseResult<GenericResponse<User>> {
        return try {
            val result = axxessCodingChallengeService
                .getSearchAsync(query).await()
            UseCaseResult.Success(result)
        } catch (e: Exception) {
            UseCaseResult.Exception(e)
        }
    }

}