package com.manish.koo.helper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

interface WatchableFlow<T> : Flow<T> {
    fun watch(scope: CoroutineScope, block: (T) -> Unit): Closable
}

private class WatchableFlowImpl<T>(private val flow: Flow<T>) : WatchableFlow<T> {
    @OptIn(InternalCoroutinesApi::class)
    override suspend fun collect(collector: FlowCollector<T>) {
        flow.collect(collector)
    }

    override fun watch(scope: CoroutineScope, block: (T) -> Unit): Closable {
        val job = onEach { block(it) }.launchIn(scope)
        return job.asClosable()
    }
}

internal fun <T> Flow<T>.asWatchableFlow(): WatchableFlow<T> = WatchableFlowImpl(this)