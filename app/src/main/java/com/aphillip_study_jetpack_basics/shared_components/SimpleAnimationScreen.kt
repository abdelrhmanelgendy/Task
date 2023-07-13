package com.aphillip_study_jetpack_basics.shared_components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt


@Composable
fun ProgressAnimationScreen() {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            MyCustomCircularProgressBar(.9f, 100, radius = 40.dp, color = Color.DarkGray, stroke = 10.dp, animDuration = 300)
            MyCustomCircularProgressBar(.9f, 200, radius = 40.dp, color = Color.Yellow, stroke = 10.dp,animDuration = 500, animDelay = 300)
            MyCustomCircularProgressBar(.9f, 500, radius = 40.dp, color = Color.Red, stroke = 10.dp,animDuration = 700, animDelay = 800)
        }

    }
}


@Composable
fun MyCustomCircularProgressBar(
    filledPercentage: Float,
    number: Int,
    fontSize: TextUnit = 25.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Yellow,
    animDuration: Int = 2000,
    animDelay: Int = 100,
    stroke: Dp = 15.dp
) {


    var isAnimPlayed by remember {
        mutableStateOf(false)
    }

    val currentAnim =
        animateFloatAsState(
            targetValue = if (isAnimPlayed) filledPercentage else 0f,

//            animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy, stiffness = Spring.StiffnessHigh)
            tween(animDuration, animDelay, easing = FastOutLinearInEasing)
        )


    LaunchedEffect(key1 = true) {
        isAnimPlayed = true
    }

    Box(
        modifier = Modifier.size(radius * 2),
        contentAlignment = Alignment.Center
    )
    {
        Canvas(modifier = Modifier.size(radius * 2)) {
            drawArc(
                color = color,
                -90f,
                360 * currentAnim.value,
                useCenter = false,
                style = Stroke(stroke.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = "${(number * currentAnim.value).roundToInt()}",
            color = color,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }

}


