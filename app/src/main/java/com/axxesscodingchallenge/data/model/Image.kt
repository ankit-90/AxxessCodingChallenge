package com.axxesscodingchallenge.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image( @SerializedName("id") var id: String,
                  @SerializedName("title") val title: String,
                  @SerializedName("images") val imageSrc: ArrayList<ImageUrl> =
                      ArrayList<ImageUrl>()

) : Parcelable {
    val imageSrc_:String
        get() = imageSrc[0].image
}
@Parcelize
data class ImageUrl(@SerializedName("link") val image:String) : Parcelable