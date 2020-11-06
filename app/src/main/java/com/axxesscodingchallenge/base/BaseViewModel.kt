package com.axxesscodingchallenge.base

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axxesscodingchallenge.data.model.SearchResponse
import com.axxesscodingchallenge.data.model.Image
import com.axxesscodingchallenge.utils.CommonUtils
import com.axxesscodingchallenge.utils.UseCaseResult
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

/**
 * @author Ankit Chandel
 * @since 04/11/20
 * <h1>BaseViewModel</h1>
 * <p>This class contains common operations for all the view models</p>
 * */
open class BaseViewModel() : ViewModel(), CoroutineScope {

    private val job = Job()
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    var state = MutableLiveData<SearchResponse>()
    val errorMessage = MutableLiveData<String>()
    val exceptionMessage = MutableLiveData<String>()
    var errorArray = MutableLiveData<List<Error>>()
    val retry = MutableLiveData<() -> Unit>()
    val showLoading = MutableLiveData<Boolean>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
    fun isValidNetwork(): Boolean {
        return CommonUtils.isNetworkAvailable()
    }
    fun isValidNetwork(retryInterface: () -> Unit): Boolean {
        val isAvailable = CommonUtils.isNetworkAvailable()
        if (!isAvailable) {
            retry.value = retryInterface
        }
        return isAvailable
    }
    fun <T : Any> apiSuccess(result: UseCaseResult<SearchResponse>) {
        when (result) {
            is UseCaseResult.Success -> {
                if (result.data.status == 200) {
                    Log.d("check","check")
                    state.value = result.data
                } else if (result.data.status != null && result.data.status!! >= 400) {
                    if (result.data.success != null) {
                        errorMessage.value = result.data.success.toString()
                    } else{
                        //errorArray.value = result.data.status?.errors
                        //for future use
                    }

                }
            }
            is UseCaseResult.Exception -> errorMessage.value = result.exception.message
        }
    }
    fun  apiSuccess(
        state: MutableLiveData<List<Image>>,
        result: UseCaseResult<SearchResponse>
    ) {
        when (result) {

            is UseCaseResult.Success -> {
                if (result.data.status == 200) {
                    state.value = result.data.data
                } else if (result.data.status != null && result.data.status!! >= 400) {
                    if (result.data.success != null) {
                        errorMessage.value = result.data.success.toString()
                    } else{
                        //errorArray.value = result.data.status?.errors
                        //for future use
                    }

                }
            }
            is UseCaseResult.Exception -> errorMessage.value = result.exception.message
        }
    }
    val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Timber.d("exception Handler${throwable.message}")
        exceptionMessage.value = throwable.message
        Timber.d("handlerException ${throwable.message} ")
    }
}
