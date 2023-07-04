package com.tasks.dev_compose_lab.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Expand
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme

private const val TAG = "DevLabMainScreen"

class DevLabMainScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationComponentTheme() {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Surface(modifier, color = MaterialTheme.colors.primary) {
        Greetings()

    }
}


@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
) {
    val names: List<String> = List(1000) { "${it}" }

    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}


@Composable
private fun Greeting(name: String) {

    val expanded = rememberSaveable { mutableStateOf(false) }

    val extraPadding = animateDpAsState(
        if (expanded.value) 48.dp else 0.dp, animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessHigh
        )
    )


    Card(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.padding(5.dp).clickable {
            expanded.value = !expanded.value
        },

        ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.value.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello, ", color = MaterialTheme.colors.secondary)
                Text(text = name, color = MaterialTheme.colors.secondary)
                if(expanded.value){
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4),
                    )
                }
            }
            IconButton(onClick = { expanded.value = !expanded.value }) {
                Icon(
                    imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded.value)
                        stringResource(id = com.tasks.navigationcomponent.R.string.show_less) else
                        stringResource(id = com.tasks.navigationcomponent.R.string.show_more)
                )
            }

        }
    }

}


//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.Button
//import androidx.compose.material.Surface
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme
//import com.tasks.navigationcomponent.ui.theme.backGroundColor
//
//class  : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            NavigationComponentTheme() {
//                Content(name = "Mostafa Mosad Elgendy")
//            }
//        }
//    }
//
//    @Composable
//    fun Content(name: String) {
//        val expanded = remember {
//            mutableStateOf(false)
//        }
//        val extraPadding =if(expanded.value) 35.dp else 0.dp
//
//        Surface(
//            color = Color.White,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Column() {
//                Row(
//                    modifier = Modifier
//                        .padding(top = 20.dp, bottom = 20.dp)
//                        .background(color = backGroundColor)
//                        .fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Column(modifier = Modifier.padding(top=10.dp, start = 10.dp,end=10.dp, bottom = extraPadding)) {
//                        Txt(txt = "Hello, ")
//                        Txt(txt = "Mosta Mosad")
//                    }
//                    Button(
//                        onClick = {
//                            expanded.value = !expanded.value
//                        }
//                    ) {
//                        Txt(txt = if (expanded.value) "Show Less" else "Show More")
//                    }
//                }
////                 Row(modifier = Modifier
////                     .padding(0.dp)
////                     .background(color = backGroundColor)
////                     .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
////                     Column(modifier = Modifier.padding(10.dp)) {
////                        Txt(txt = "Hello, ")
////                        Txt(txt = "Mosta Mosad")
////                    }
////                    Button(
////                        onClick = {
////
////                        }
////                    ) {
////                        Txt(txt = "Show more")
////                    }
////                }
//            }
//        }
//    }
//
//    @Composable
//    fun Txt(txt: String) {
//        Text(
//            text = txt,
//            color = Color.White,
//            fontWeight = FontWeight.ExtraBold,
//            fontStyle = FontStyle.Italic
//        )
//    }
//
//}