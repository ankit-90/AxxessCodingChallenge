package com.axxesscodingchallenge.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.internal.operators.maybe.MaybeDoAfterSuccess

data class SearchResponse(
    @SerializedName("data")
    @Expose
    var data: List<Image>? = null,
    @SerializedName("status")
    @Expose
    var status: Int? = null,
    @SerializedName("success")
    @Expose
    var success: Boolean =  false
)