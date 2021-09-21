package com.manish.koo.post

import com.google.gson.annotations.SerializedName

class PostResponse(
    @field:SerializedName("data")
    val data: List<PostData>
)