package com.axxesscodingchallenge.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

import com.axxesscodingchallenge.base.BaseApp
import java.security.AccessController.getContext

object CommonUtils {
    private val context = BaseApp.getContext()

    fun isNetworkAvailable(): Boolean {

        val connectivity =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivity.activeNetworkInfo
        if (info != null) {
            return info.state == NetworkInfo.State.CONNECTED || info.state == NetworkInfo.State.CONNECTING
        }
        return false
    }


}