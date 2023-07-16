package com.tasks.view_model_plus

import androidx.lifecycle.ViewModel

import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class ViewModelPlus() : ContainerHost<PlusStates, Nothing>, ViewModel() {
    override val container: Container<PlusStates, Nothing> = container(PlusStates.PlusIdleState())




    fun onHandleEvent(eventPlus: EventPlus){
        when(eventPlus){
            is EventPlus.LoginUser->{
                startLogin(eventPlus.userName,eventPlus.password)
            }
            is EventPlus.RegisterUser->{
                startRegister(eventPlus.userName,eventPlus.password)

            }
            else->{
                logOutUser()
            }
        }
    }

    private fun startRegister(userName: String, password: String) {

    }

    private fun startLogin(userName: String, password: String) {


    }

    private fun logOutUser() {


    }



}