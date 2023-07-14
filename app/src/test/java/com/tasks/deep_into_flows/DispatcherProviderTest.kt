package com.tasks.deep_into_flows

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

class DispatcherProviderTest:CoroutineDispatcherProvider {
    val testCoroutineDispatcher =TestCoroutineDispatcher()
    override val main: CoroutineDispatcher
        get() = testCoroutineDispatcher
    override val io: CoroutineDispatcher
        get() =testCoroutineDispatcher
    override val default: CoroutineDispatcher
        get() = testCoroutineDispatcher
}