package com.tasks.navigationcomponent

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.random.Random

class SignUpToNewsletterViewModel(
    private val submitEmailUseCase: SubmitEmailUseCase,
    private val router: SubmitEmailRouter,
    private val schedulerFacade: SchedulerFacade
) : ViewModel() {

    var buttonEnabled = ObservableField<Boolean>()
    var input = ObservableField<String>("")
    var loading = ObservableField<Boolean>(false)
    var errorMessage = ObservableField<Int>()

    init {

    }

    fun textChange()
    {
        val observeOn =
            submitEmailUseCase.execute(input.get()!!).subscribeOn(schedulerFacade.background)
                .observeOn(schedulerFacade.main)
        observeOn.subscribe(object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onComplete() {
                Log.d("TAG121", "execute: ")

                buttonEnabled.set(true)

     }

            override fun onError(e: Throwable) {
                Log.d("TAG121", "execute: "+e.message)

                buttonEnabled.set(false)
            }
        })
    }
    fun onSubmitClick() {
        loading.set(true)
        if(Random.nextBoolean())
        {
            router.showSuccess()
        }else
        {
            errorMessage.set(R.string.network_error)
        }
  }
}

class SomeViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = SignUpToNewsletterViewModel(
        SubmitEmailUseCaseIMPL(), SubmitEmailRouterImpl(), SchedulerFacadeImpl()
    ) as T
}

interface SubmitEmailRouter {
    fun showSuccess()
}


interface SubmitEmailUseCase {
    fun execute(email: String): Completable
}

sealed class SubmitEmailError : Throwable() {
    object EmailAlreadyInUse : SubmitEmailError()
    object NetworkError : SubmitEmailError()
}


class SubmitEmailRouterImpl : SubmitEmailRouter {
    override fun showSuccess() {

    }

}


interface SchedulerFacade {
    val main: Scheduler
    val background: Scheduler
}

class SchedulerFacadeImpl : SchedulerFacade {
    override val main: Scheduler
        get() = AndroidSchedulers.mainThread()
    override val background: Scheduler
        get() = Schedulers.io()
}

class SubmitEmailUseCaseIMPL : SubmitEmailUseCase {
    override fun execute(email: String): Completable {
        return Completable.create { emmiter ->
            kotlin.runCatching {

                Log.d("TAG121", "execute: "+email)
                if (email.isEmpty()) {
                    emmiter.onError(Throwable("InValid Email Format"))
                    return@runCatching
                }
                val matches = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                Log.d("TAG121", "execute: "+matches)

                if (matches) {
                    emmiter.onComplete()

                } else {
                    emmiter.onError(Throwable("InValid Email Format"))

                }


            }.onFailure {
                emmiter.onError(it)
            }
        }
    }
}

 