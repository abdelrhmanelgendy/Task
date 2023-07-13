package com.aphillip_study_jetpack_basics.shared_components

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.provider.Settings
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DropDown3DAnim(context:Context) {
    DropDown2(
        text = "Toggle Up& Down", modifier = Modifier
            .fillMaxSize()
            .padding(15.dp), context = context
    ) {
        Text(
            text = "this is now visible",
            color = Color.White,
            modifier = Modifier
                .background(Color.Green)
                .height(100.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun DropDown2(
    text: String,
    modifier: Modifier = Modifier,
    initiallyOpened: Boolean = false,
    context: Context,
    content: @Composable () -> Unit
) {
    var isOpen by remember {
        mutableStateOf(initiallyOpened)
    }
    val alpha = animateFloatAsState(
        targetValue = if (isOpen) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300
        )
    )
    val rotateX = animateFloatAsState(
        targetValue = if (isOpen) 0f else -90f,
        animationSpec = tween(
            durationMillis = 300
        )
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Open or close the drop down",
                tint = Color.White,
                modifier = Modifier
                    .clickable {
                        isOpen = !isOpen
                        openLocationServices(context)

                    }
                    .scale(1f, if (isOpen) -1f else 1f)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    transformOrigin = TransformOrigin(0.5f, 0f)
                    rotationX = rotateX.value
                }
                .alpha(alpha.value)
        ) {
            content()
        }
    }

    @Composable
    fun DropDown(
        textHeader: String,
        modifier: Modifier = Modifier,
        initialOpenState: Boolean = false,
        content: @Composable () -> Unit,
        context: Context
    ) {
        var isOpen by remember {
            mutableStateOf(initialOpenState)
        }

        val alpha =
            animateFloatAsState(
                targetValue = if (isOpen)
                    1f else 0f, animationSpec = tween(300)
            )
        val rotateX =
            animateFloatAsState(
                targetValue = if (isOpen)
                    0f else -90f, animationSpec = tween(300)
            )


        Column(modifier = modifier.fillMaxSize()) {

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = textHeader, color = Color.Yellow, fontSize = 18.sp)
                IconButton(onClick = {

                    isOpen = !isOpen
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier.scale(1f, scaleY = if (isOpen) -1f else 1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Box(contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    transformOrigin = TransformOrigin(.5f, 0f)
                    rotationX = rotateX.value

                }
                .alpha(alpha.value)) {
                content()
            }
        }
    }

}

fun openLocationServices(context: Context) {
    context.run {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
    }
}