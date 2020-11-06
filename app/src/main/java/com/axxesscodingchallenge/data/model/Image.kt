package com.axxesscodingchallenge.data.model

import com.google.gson.annotations.SerializedName

data class Image( @SerializedName("id") val id: String,
                  @SerializedName("title") val title: String,
                  @SerializedName("images") val imageSrc: ArrayList<ImageUrl> =
                      ArrayList<ImageUrl>()

) {
}

data class ImageUrl(@SerializedName("link") val image:String)