package com.axxesscodingchallenge.base

import androidx.annotation.StringRes

/**
 * @author Ankit Chandel
 * @since 04/11/20
 * <h1>BaseView</h1>
 * <p>Base view any view must implement.</p>
 * */
interface BaseView {

    fun baseApplication(): BaseApp

    /**
     * show loader while API is hit
     */
    fun showLoading()
    /*
    * hide loader on API success
    * */
    fun hideLoading()
    /*
    *
    * show retry view when network is not available
    * */
    fun showRetry(throwable: Throwable)
    /*
    *
    * hide retry view when network is available
    * */
    fun hideRetry()
    /*
    * on retry click hit API again
    * */
    fun onRetry()


}