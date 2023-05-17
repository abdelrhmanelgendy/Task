package com.tasks.navigationcomponent


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.databinding.Observable


class MainActivity : AppCompatActivity() {
    val signUpToNewsletterViewModel by viewModels<SignUpToNewsletterViewModel> {
        SomeViewModelFactory()
    }

    lateinit  var et:EditText
    lateinit  var progress:ProgressBar
    lateinit  var btn:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        et=findViewById(R.id.et)
        progress=findViewById(R.id.progress)
        btn=findViewById(R.id.btn)
        btn.isVisible=signUpToNewsletterViewModel.buttonEnabled.get()?:false
        progress.isVisible=signUpToNewsletterViewModel.loading.get()?:false

        et.addTextChangedListener {
            signUpToNewsletterViewModel.input.set(it.toString())
            signUpToNewsletterViewModel.textChange()


        }
        val someCallback = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                btn.isVisible=signUpToNewsletterViewModel.buttonEnabled.get()?:false

            }

        }
        signUpToNewsletterViewModel.buttonEnabled.addOnPropertyChangedCallback(someCallback)
        signUpToNewsletterViewModel.loading.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                 progress.isVisible=signUpToNewsletterViewModel.loading.get()?:false

            }

        })
     }

    fun login(view: View) {
        signUpToNewsletterViewModel.onSubmitClick()
    }

}

