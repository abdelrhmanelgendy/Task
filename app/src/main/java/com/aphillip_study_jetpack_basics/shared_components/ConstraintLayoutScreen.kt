package com.aphillip_study_jetpack_basics.shared_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension


@Composable
fun ConstraintLayoutScreen() {


    val constraintSet = getConstrainsSet()

    ConstraintLayout(constraintSet, modifier = Modifier.fillMaxSize()) {
        Box(Modifier.background(Color.Red).layoutId("redBox"))
        Box(Modifier.background(Color.Green).layoutId("greenBox"))
    }


}

fun getConstrainsSet() = ConstraintSet {

    val redBox = createRefFor("redBox")
    val greenBox = createRefFor("greenBox")
    constrain(redBox) {

        top.linkTo(parent.top)
        start.linkTo(parent.start)
        width = Dimension.percent(.5f)
        height= Dimension.value(80.dp)


    }

    constrain(greenBox) {

        top.linkTo(redBox.bottom)
        start.linkTo(redBox.end)
        end.linkTo(parent.end)
        width = Dimension.fillToConstraints
        height= Dimension.value(80.dp)


    }


}
