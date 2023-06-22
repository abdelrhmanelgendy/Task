package com.tasks.gym_dashboard.presentation


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.tasks.gym_dashboard.data.model.GymItem
import androidx.compose.foundation.lazy.items

private const val TAG = "MainActivityTAG"
lateinit var gymViewModel1: GymsViewModel

class DashboardActivity() : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "DASH BOARD", Toast.LENGTH_SHORT).show()

        val gymViewModel: GymsViewModel by viewModels()
        gymViewModel1 = gymViewModel


        lifecycleScope.launchWhenStarted {
            gymViewModel.getListOfGyms()
        }





        setContent {
            Scaffold(topBar = {
                TopAppBar(modifier = Modifier.height(35.dp), title = {
                    Text(text = "Top App Bar")
                }, backgroundColor = Color.Black, contentColor = Color.White, elevation = 10.dp
                )
            }, content = {
                ListOfGymItems()


            })


        }
    }


    @Composable
    private fun ListOfGymItems() {

        if (gymViewModel1.gymsState.value.progress) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = Color.Magenta)

            }
        }
        if(gymViewModel1.gymsState.value.gyms.isNotEmpty()) {
            LazyColumn() {
                items(gymViewModel1.gymsState.value .gyms) { item ->
                    Log.d(TAG, "ListOfGymItems: " + item.toString())
                    GymItem(item) {
                        startActivity(
                            Intent(
                                this@DashboardActivity,
                                GymDetailsActivity::class.java
                            )
                        )
                    }
                }


            }
        }
        if(gymViewModel1.gymsState.value.errorMsg.isNotEmpty()) {
         Text(text =gymViewModel1.gymsState.value.errorMsg.toString() )
        }


    }

    @Composable
    fun GymItem(gymItem: GymItem, onClick: () -> Unit) {

        val icon = if (gymItem.is_Favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
        Card(elevation = 8.dp, modifier = Modifier
            .padding(2.dp)
            .clickable {
                onClick()
            }
        ) {
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
                    contentDescription = "",
                    icon,
                    modifier = Modifier.weight(weight = .1f)

                ){
                    gymViewModel1.toggleIsFavorite(gymItem)
                }

            }
        }


    }

    private fun getIcon(gymItem: GymItem): ImageVector {
        return  if(gymItem.is_Favorite)
        {Icons.Filled.Favorite}else{
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
}




