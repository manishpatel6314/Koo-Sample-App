package com.manish.koo.remoteUtils

import com.manish.koo.post.PostResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface WebService {

    @GET
    fun fetch(@Url url : String, @Query("page")page : Int): Call<PostResponse>
}