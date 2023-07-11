package com.tasks.deep_into_flows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowViewModel constructor(val dispatchers: CoroutineDispatcherProvider) : ViewModel() {


    private var _counterStateFlow = MutableStateFlow(5)
    var counterStateFlow = _counterStateFlow.asStateFlow()


    private var _counterSharedFlow = MutableSharedFlow<Int>()
    var counterSharedFlow = _counterSharedFlow.asSharedFlow()


    fun countDownFlow() = flow {
        for (i in 5 downTo 0) {
            emit(i)


            delay(1000)
        }
    }.flowOn(dispatchers.main)


    fun incremeant() {
        _counterStateFlow.value += 11
    }

    fun squaresNumbers(num: Int) {
        viewModelScope.launch(dispatchers.main) {
            _counterSharedFlow.emit(num * num)
        }
    }


//    val numberFlow = flow {
//
//
//        val items = arrayListOf(1, 1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10)
//        emit(items.first())
//
//
//        items.forEach {
//            delay(1000L)
//            emit(it)
//        }
//
//    }

//    fun collectMyFlow() {
//
//
//        viewModelScope.launch {
//            val count = DoBusinessLoginUseCase()(numberFlow)
//                .onEach {
//                    Log.d("TAG123", "collectMyFlow: $it")
//                }.count {
//                    it % 2 == 0
//                }
//            Log.d("the count is", "collectMyFlow: $count")
//        }
//
//
//    }
}