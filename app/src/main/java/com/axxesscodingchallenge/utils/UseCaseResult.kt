package com.axxesscodingchallenge.utils

sealed class UseCaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Exception(val exception: Throwable) : UseCaseResult<Nothing>()
}