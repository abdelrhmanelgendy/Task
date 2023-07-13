package com.tasks.finger_print_manager

sealed class FingerPrintHandlerState{

    object Idle:FingerPrintHandlerState()
    class  Success:FingerPrintHandlerState()
    data class Error(val error:String):FingerPrintHandlerState()


}
