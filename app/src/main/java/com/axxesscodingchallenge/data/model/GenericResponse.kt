package com.axxesscodingchallenge.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenericResponse<T : Any>(
    @SerializedName("data")
    @Expose
    var data: T? = null,
    /*@SerializedName("status")
    @Expose
    var status: Status? = null*/
)