package com.axxesscodingchallenge.room.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.PropertyKey

@Parcelize
@Entity(tableName = "Comment")
data class Comment(@PrimaryKey(autoGenerate = true)
                   var id: Int? = null,
                   var type:String? = null,
                   var itemId:String? = null,
                   var comment: String? = null,
                   var whenCreated: Long = System.currentTimeMillis(),
                   var whenUpdated: Long = System.currentTimeMillis()

) : Parcelable