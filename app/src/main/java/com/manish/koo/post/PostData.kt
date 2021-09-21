package com.manish.koo.post

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "postData")
data class PostData(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("user_id")
    val userId: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("body")
    val body: String,
    @field:SerializedName("masterKey")
    var masterKey: Int = -1
)