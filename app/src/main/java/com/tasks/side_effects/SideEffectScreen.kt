package com.tasks.side_effects

import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.tasks.finger_print_manager.FingerPrintHandlerKotlin
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

class SideEffectScreen : ComponentActivity() {
    private lateinit var mFingerprintManager: FingerprintManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mFingerprintManager =
                getSystemService(FingerprintManager::class.java) as FingerprintManager
            val fingerScannerAvailableAndSet =
                FingerPrintHandlerKotlin().isFingerScannerAvailableAndSet(this, mFingerprintManager)

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    fingerScannerAvailableAndSet.collectIndexed { index, value ->
                        Log.d("TAG", "onCreate: "+value)
                    }
                }
            }

        }
        setContent {
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()


            val counter by produceState(initialValue = "False") {

                delay(5000)
                value = "My Name Is Mostafa"
            }
            NavigationComponentTheme() {
                Scaffold(scaffoldState = scaffoldState) {


//                if(counter.length%5==0){
//                    LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
//                        scaffoldState.snackbarHostState.showSnackbar("number is divisible")
//                    }
//                }

                    TextButton(onClick = {

                    }) {
                        Text(text = "CLick Me $counter")
                    }
                    
                }
            }
        }
    }

}