package com.tasks.deep_into_flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class DoBusinessLoginUseCase {

    operator fun invoke(numberFlow: Flow<Int>)=numberFlow.map {
        it*it
    }
}
