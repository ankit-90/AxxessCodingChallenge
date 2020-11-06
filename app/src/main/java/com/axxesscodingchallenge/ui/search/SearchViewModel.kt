package com.axxesscodingchallenge.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.axxesscodingchallenge.base.BaseViewModel
import com.axxesscodingchallenge.data.model.Image

import com.axxesscodingchallenge.utils.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(private val searchRepository: SearchRepository) : BaseViewModel() {
    var images = MutableLiveData<List<Image>>()

    fun getSearchResults(query: String) {
        if(NetworkHelper.isNetworkConnected()) {
            showLoading.value = true
            launch(handler) {
                val result =
                    withContext(Dispatchers.IO) {
                        searchRepository.getSearchResults(query)
                    }
                withContext(Dispatchers.Main) {
                    showLoading.value = false
                    apiSuccess(images,result)

                }
            }
        }

    }

}