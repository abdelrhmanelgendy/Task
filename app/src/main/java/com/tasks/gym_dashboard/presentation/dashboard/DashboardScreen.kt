package com.tasks.gym_dashboard.presentation


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.tasks.gym_dashboard.SemanticsDescriptions
import com.tasks.gym_dashboard.domain.GymItem
import com.tasks.gym_dashboard.presentation.dashboard.GymScreenState

private const val TAG = "MainActivityTAG"

@Composable
fun GymsScreen(
    gymsScreenState: GymScreenState, onToggleIsFavorite: (id: Int, isFavorite: Boolean) -> Unit
) {


    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier.height(35.dp), title = {
                Text(text = "Top App Bar")
            }, backgroundColor = Color.Black, contentColor = Color.White, elevation = 10.dp
        )
    }, content = {
        ListOfGymItems(gymsScreenState, onToggleIsFavorite)


    })


}


@Composable
private fun ListOfGymItems(
    gymsScreenState: GymScreenState, onToggleIsFavorite: (id: Int, isFavorite: Boolean) -> Unit
) {

    if (gymsScreenState.progress) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.semantics {
                contentDescription = SemanticsDescriptions.LOADING
            }, color = Color.Magenta)

        }
    }
    if (gymsScreenState.gyms.isNotEmpty()) {
        LazyColumn() {
            items(gymsScreenState.gyms) { item ->
                Log.d(TAG, "ListOfGymItems: " + item.toString())
                GymItemComp(item, onToggleIsFavorite) {}
            }


        }
    }
    if (gymsScreenState.errorMsg.isNotEmpty()) {
        Text(text = gymsScreenState.errorMsg.toString())
    }


}

@Composable
fun GymItemComp(
    gymItem: GymItem,
    onToggleIsFavorite: (id: Int, isFavorite: Boolean) -> Unit,
    onClick: () -> Unit
) {

    val icon = if (gymItem.is_Favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
    Card(elevation = 8.dp, modifier = Modifier
        .padding(2.dp)
        .clickable {
            onClick()
        }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            DefaultIcon(
                contentDescription = "",
                Icons.Filled.LocationOn,
                modifier = Modifier.weight(weight = .1f)

            )
            Column(modifier = Modifier.weight(weight = .6f)) {
                Text(
                    text = gymItem.gym_name ?: "",
                    style = MaterialTheme.typography.h6,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Red

                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = gymItem.gym_location ?: "",
                        style = MaterialTheme.typography.subtitle2,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black

                    )
                }

            }
            DefaultIcon(
                contentDescription = "", icon, modifier = Modifier.weight(weight = .1f)

            ) {
                onToggleIsFavorite(gymItem.id, gymItem.is_Favorite)
            }

        }
    }


}

private fun getIcon(gymItem: GymItem): ImageVector {
    return if (gymItem.is_Favorite) {
        Icons.Filled.Favorite
    } else {
        Icons.Filled.FavoriteBorder
    }
}


@Composable
fun DefaultIcon(
    contentDescription: String, icon: ImageVector, modifier: Modifier, onClick: () -> Unit = {}
) {
    Icon(icon, contentDescription, modifier = modifier.clickable {
        onClick()
    })

}




