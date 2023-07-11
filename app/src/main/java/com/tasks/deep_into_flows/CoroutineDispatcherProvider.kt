package com.tasks.deep_into_flows

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherProvider {
    val main:CoroutineDispatcher
    val io:CoroutineDispatcher
    val default:CoroutineDispatcher

}

