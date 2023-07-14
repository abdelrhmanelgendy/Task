package com.tasks.deep_into_flows

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nameisjayant.threadsapp.utils.noRippleEffect
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FlowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val flowViewModel by viewModels<FlowViewModel>()

            val count = flowViewModel.counterStateFlow.collectAsState()
//            val doBusinessLoginUseCase = DoBusinessLoginUseCase()(flowViewModel.numberFlow)

            collectLatestLifeCycleFlow(flowViewModel.counterStateFlow) { num ->
                Log.d("TAG124", "onCreate: $num")
                if (num > 10) {
                    Toast.makeText(this, "This is toast", Toast.LENGTH_SHORT).show()
                }
            }

            collectSharedFlow(flowViewModel.counterSharedFlow) { num ->
                Log.d("TAG124", "onCreate: $num")
                if (num == 10) {
                    Toast.makeText(this, "This is toast", Toast.LENGTH_SHORT).show()
                }
            }


            NavigationComponentTheme() {
                Surface(color = Color.Black, modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .padding(100.dp)
                            .fillMaxSize()
                    ) {
                        Column() {
                            Text(
                                text = count.value.toString(),
                                color = Color.White
                            )
                            TextButton(
                                onClick = {
//                                    flowViewModel.increamentTheShared(2)

                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.DarkGray,
                                    contentColor = Color.White
                                ), modifier = Modifier.noRippleEffect {

                                }
                            ) {
                                Text(text = "INCREMEANT ${count.value}")
                            }
                        }
                    }
                }
            }

        }
    }


    fun <T> ComponentActivity.collectLatestLifeCycleFlow(
        flow: Flow<T>,
        collect: suspend (T) -> Unit
    ) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collectLatest(collect)
            }
        }
    }

    fun <T> collectSharedFlow(flow: SharedFlow<T>, collect: suspend (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect(collect)
            }
        }
    }


    @Composable
    fun compose() {


        val x = 5

        DisposableEffect(key1 = x) {


            onDispose {

            }
        }

//        buildButton()
    }
}

