package com.tasks.dev_compose_lab.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme

class AnimationsDeepIn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationComponentTheme {
                Content()

            }
        }
    }

    @Composable
    fun Content() {

        var isRounded by remember {
            mutableStateOf(false)
//
            //
        }

        val finiteTransition = updateTransition(isRounded)
//        val colorTransition by finiteTransition.animateFloat(
//            initialValue = 0f,
//            targetValue =1f,
//            animationSpec = infiniteRepeatable(
//                animation = tween(2000),
//                repeatMode = RepeatMode.Reverse
//            )
//        )
        val animateInt by finiteTransition.animateInt() { rounded ->
            if (rounded) 200 else 50

        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize(
            )
        ) {
            Box(modifier = Modifier.height(200.dp))
            Button(onClick = {
                isRounded = !isRounded
            }) {
                Text(text = "Toggle")
            }


            Box(
                modifier = Modifier
                    .size(animateInt.dp)
                     .background(Color.Red)

            )


        }
    }
}

/*

        val transition = updateTransition(targetState = isRounded, label = null)

        val borderRadius by transition.animateInt(
            transitionSpec = { tween(2000) }, label = "borderRadius"
        ) { rounded ->
            if (rounded) 100 else 0
        }

        val boxColor by transition.animateColor(
            transitionSpec = { tween(2000) }, label = "color"
        ) { rounded ->
            if (rounded) Color.LightGray else Color.DarkGray

        }
 */
/*
      val roundValue by animateFloatAsState(
                targetValue = if (isRounded) 1f else 0f,

                animationSpec = tween(durationMillis = 5000, delayMillis = 80)
//                animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy, stiffness = Spring.StiffnessHigh)
            )
 */
/*
       AnimatedVisibility(
        visible = visibilityState,
        modifier = Modifier
            .width(200.dp)
            .height(300.dp),
        enter = expandIn(),
        exit = shrinkOut(),

        ) {

        Box(modifier = Modifier.background(Color.Red)) {

        }
 */