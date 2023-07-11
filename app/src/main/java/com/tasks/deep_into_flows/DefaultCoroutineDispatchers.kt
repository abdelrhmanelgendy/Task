package com.tasks.deep_into_flows

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultCoroutineDispatchers :CoroutineDispatcherProvider{
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val io: CoroutineDispatcher
        get() =Dispatchers.IO
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
}

