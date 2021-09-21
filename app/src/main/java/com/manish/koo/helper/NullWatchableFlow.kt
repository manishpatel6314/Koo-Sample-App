package com.manish.koo.helper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class NullWatchableFlow<T> internal constructor(private val watchableFlow: WatchableFlow<T>) :
    WatchableFlow<T> by watchableFlow {
    /**
     * Two reasons for overriding
     * 1. Autocomplete does not work in iOS if function is not a member of class
     * 2. The type information is missing in case of iOS
     */
    @Suppress("RedundantOverride")
    override fun watch(scope: CoroutineScope, block: (T) -> Unit): Closable =
        watchableFlow.watch(scope, block)
}

class NonNullWatchableFlow<T : Any> internal constructor(private val watchableFlow: WatchableFlow<T>) :
    WatchableFlow<T> by watchableFlow {
    /**
     * Two reasons for overriding
     * 1. Autocomplete does not work in iOS if function is not a member of class
     * 2. The type information is missing in case of iOS
     */
    @Suppress("RedundantOverride")
    override fun watch(scope: CoroutineScope, block: (T) -> Unit): Closable =
        watchableFlow.watch(scope, block)
}

fun <T : Any> Flow<T>.asNonNullWatchableFlow(): NonNullWatchableFlow<T> =
    NonNullWatchableFlow(asWatchableFlow())

fun <T> Flow<T>.asNullWatchableFlow(): NullWatchableFlow<T> = NullWatchableFlow(asWatchableFlow())