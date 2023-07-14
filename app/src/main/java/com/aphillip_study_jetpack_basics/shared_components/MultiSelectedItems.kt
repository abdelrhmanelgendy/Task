package com.aphillip_study_jetpack_basics.shared_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SelectedItems() {

    var items by rememberSaveable {
        mutableStateOf(
            (1..20).map {
                ListItem(it, "item is $it", false)
            }
        )
    }

    LaunchedEffect(key1 = true){
        delay(5000)
        items=items.take(5)
    }
    //
    
    LazyColumn(Modifier.fillMaxSize()) {
        items(items) { item ->
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .padding(10.dp),
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = item.name, fontSize = 16.sp, color = Color.White)
                    Icon(
                        imageVector = if (item.selected) Icons.Default.CheckCircle else Icons.Default.Circle,
                        tint = Color.White,
                        contentDescription = "",

                        modifier = Modifier
                            .clickable {

                                items = items.mapIndexed { index, listItem ->
                                    if (item.id == items[index].id) {
                                        listItem.copy(selected = !listItem.selected)
                                    } else listItem
                                }
                            }
                            .height(25.dp)
                            .width(25.dp))
                }
            }
        }
    }


}


data class ListItem(val id: Int, var name: String, var selected: Boolean)