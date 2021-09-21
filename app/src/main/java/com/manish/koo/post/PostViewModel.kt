package com.manish.koo.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingConfig
import com.kuuurt.paging.multiplatform.PagingResult
import com.manish.koo.helper.PagerWrapperClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

class PostViewModel @Inject constructor(
    var repo: PostRepository
) : ViewModel() {
    private val clientScope: CoroutineScope = viewModelScope

    @ExperimentalCoroutinesApi
    @OptIn(FlowPreview::class)
    fun getPagerData(): PagerWrapperClass<Int, PostData> {

        val pager: Pager<Int, PostData> = Pager(
            clientScope,
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
            initialKey = 1,
            getItems = { currentKey, limit ->

                var result: List<PostData> = emptyList()
                result = repo.fetchData(currentKey)
                PagingResult(
                    result,
                    currentKey,
                    prevKey = { currentKey - 1 },
                    nextKey = { currentKey + 1 }
                )
            }
        )
        return PagerWrapperClass(
            pager = pager
        )
    }
}