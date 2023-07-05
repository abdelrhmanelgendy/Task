package com.tasks.motion_layout_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.tasks.navigationcomponent.R
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme

class MotionLayoutScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationComponentTheme {
                var progress by remember {
                    mutableStateOf(0f)
                }
                Column() {
                    ScreenContent(process = progress)
                    Spacer(modifier = Modifier.height(100.dp))
                    Slider(
                        value = progress,
                        onValueChange = {
                            progress = it
                        },
                        modifier = Modifier
                            .padding(horizontal = 32.dp),
                        colors = SliderDefaults.colors(
                            thumbColor = Color.Red,
                            activeTrackColor = Color.Blue,
                            inactiveTrackColor = Color.Gray
                        )
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMotionApi::class)
    @Composable
    private fun ScreenContent(process: Float) {


        val context = LocalContext.current


         val motionScene = remember {
            context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()

        }
        MotionLayout(
            motionScene = MotionScene(
                content = motionScene
            ), progress = process, modifier = Modifier.fillMaxWidth()
        ) {

            val properties =motionProperties(id = "profile_pic")
            val properties2 =motionProperties(id = "username")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .layoutId("box")

            )
          val  color =properties.value.color("background")
            Image(
                painter = painterResource(id = R.drawable.download),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .border(width = 3.dp, color=color, shape = CircleShape)
                    .layoutId("profile_pic")

            )
            Text(
                text = "Abdelrhman Mosad",
                fontSize = 20.sp,
                modifier = Modifier.layoutId("username"),
                fontWeight = FontWeight.ExtraBold,color=color
            )


        }


    }
}