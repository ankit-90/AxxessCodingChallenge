package com.axxesscodingchallenge.ui.search

import androidx.lifecycle.MutableLiveData
import com.axxesscodingchallenge.base.BaseViewModel
import com.axxesscodingchallenge.data.model.GenericResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(private val searchRepository: SearchRepository) : BaseViewModel() {
    var emailVerification = MutableLiveData<GenericResponse<Any>>()

    fun getSearchResults(query: String) {
        showLoading.value = true
        launch(handler) {
            val result =
                withContext(Dispatchers.IO) {
                    searchRepository.getSearchResults(query)
                }
            withContext(Dispatchers.Main) {
                showLoading.value = false
                apiSuccess(result)
            }
        }

    }

}