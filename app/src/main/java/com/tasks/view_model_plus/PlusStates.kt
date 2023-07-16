package com.tasks.view_model_plus

sealed class PlusStates{
    class PlusIdleState:PlusStates()
    object PlusLoadingState:PlusStates()
    object PlusSuccessState:PlusStates()
    object PlusErrorState:PlusStates()


}
