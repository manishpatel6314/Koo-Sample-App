package com.manish.koo.post

import com.manish.koo.helper.AppExecutors
import com.manish.koo.helper.BaseDataSource
import com.manish.koo.remoteUtils.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val webService: WebService,
    private val appExecutors: AppExecutors,
    private val dao: PostDao,
) {

    suspend fun fetchData(page: Int): List<PostData>{
        var result = mutableListOf<PostData>()
        withContext(Dispatchers.IO){
            val call = webService.fetch(url = "https://gorest.co.in/public/v1/posts", page = page)
            val response = BaseDataSource.Builder<PostResponse>().addCall(call).build().execute()
            if(!response.data?.data.isNullOrEmpty())
                response.data?.data?.let { result.addAll(it) }
        }
        return result
    }
}