package com.manish.koo.helper

import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
public data class PagerWrapperClass<K : Any, V : Any> @ExperimentalCoroutinesApi constructor(
    val pager: Pager<K, V>,
    val pagingData: NonNullWatchableFlow<PagingData<V>> = pager.pagingData.asNonNullWatchableFlow()
)