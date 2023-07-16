package com.tasks.view_model_plus

sealed class EventPlus{



    data class LoginUser(val userName:String,val password:String):EventPlus()
    data class RegisterUser(val userName:String,val password:String):EventPlus()
    object Logout:EventPlus()
}
