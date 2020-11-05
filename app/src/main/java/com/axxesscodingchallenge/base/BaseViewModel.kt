package com.axxesscodingchallenge.base

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axxesscodingchallenge.data.model.GenericResponse
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
 * <p>Description of class</p>
 * */
open class BaseViewModel() : ViewModel(), CoroutineScope {
    private val job = Job()
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    var state = MutableLiveData<GenericResponse<*>>()
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
    fun <T : Any> apiSuccess(result: UseCaseResult<GenericResponse<T>>) {
        when (result) {
            is UseCaseResult.Success -> {
                /*if (result.data.status?.code == 200) {
                    state.value = result.data
                } else if (result.data.status?.code != null && result.data.status?.code!! >= 400) {
                    if (result.data.status?.errors != null && result.data.status?.errors!!.isEmpty()) {
                        errorMessage.value = result.data.status?.message
                    } else
                        errorArray.value = result.data.status?.errors
                }*/
            }
            is UseCaseResult.Exception -> errorMessage.value = result.exception.message
        }
    }
    fun <T : Any> apiSuccess(
        state: MutableLiveData<GenericResponse<T>>,
        result: UseCaseResult<GenericResponse<T>>
    ) {
        when (result) {
            is UseCaseResult.Success -> {
               /* if (result.data.status?.code == 200) {
                    state.value = result.data
                } else if (result.data.status?.code != null && result.data.status?.code!! >= 400) {
                    if (result.data.status?.errors != null && result.data.status?.errors!!.isEmpty()) {
                        errorMessage.value = result.data.status?.message
                    } else
                        errorArray.value = result.data.status?.errors
                }*/
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
