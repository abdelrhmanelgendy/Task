package com.tasks.sql_lite_open_helper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme

@OptIn(ExperimentalMaterialApi::class)
class SqlScreen : ComponentActivity() {
    private val TAG = "SqlScreenTAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dbHelper: MyDatabaseHelper by lazy {
            MyDatabaseHelper(this)
        }

        setContent {

            val personsData = remember { mutableStateListOf<PersonData>() }
            NavigationComponentTheme {
                Surface(color = Color.DarkGray) {
                    LoginScreen()

                }
//                Column(
//                    verticalArrangement = Arrangement.Top,
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier.fillMaxSize()
//                ) {
//
//                    SqlButton("Insert") {
//                        val insertResult = dbHelper.insertData(
//                            PersonData(
//                                name = "mostafa mosad elgendy",
//                                age = 25,
//                                email = "abdelrhman mosad elgendy"
//                            )
//                        )
//                        val allDataResult = dbHelper.getAllData()
//                        personsData.clear()
//                        personsData.addAll(allDataResult)
//
//                        Log.d(TAG, "onCreate: insertResult  $insertResult")
//                    }
//                    SqlButton("Update") {
//                        val updateResult = dbHelper.updateData(
//                            PersonData(
//                                id = 5,
//                                name = "mostafa mosad elgendy",
//                                age = 25,
//                                email = "abdelrhman mosad elgendy"
//                            )
//                        )
//                        val allDataResult = dbHelper.getAllData()
//                        personsData.clear()
//                        personsData.addAll(allDataResult)
//
//                        Log.d(TAG, "onCreate: updateResult  $updateResult")
//                    }
//                    SqlButton("Get Single") {
//                        val person = dbHelper.getData(1)
//
//                        Log.d(TAG, "onCreate: insertResult  ${person.toString()}")
//                    }
//                    SqlButton("Get All") {
//                        val allDataResult = dbHelper.getAllData()
//                        personsData.clear()
//                        personsData.addAll(allDataResult)
//                        Log.d(TAG, "onCreate: AllDataResult  ${allDataResult.toString()}")
//                    }
//
//                    LazyColumn(
//                        Modifier
//                            .fillMaxHeight()
//                            .fillMaxWidth()
//                    ) {
//                        items(personsData) { it ->
//
//
//
//
//                            PersonCard(it){
//
//                                dbHelper.deleteData(it.id)
//                                val allDataResult = dbHelper.getAllData()
//                                personsData.clear()
//                                personsData.addAll(allDataResult)
//                            }
//                        }
//                    }
//
//
//                }
            }


        }
    }

    @Composable
    private fun PersonCard(it: PersonData, onClick: () -> Unit) {

        Card(elevation = 2.dp,
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clickable { onClick() }

        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                Row {
                    Text(
                        text = "ID " + it.id.toString(),
                        modifier = Modifier.fillMaxWidth(.2f),
                        color = Color.Yellow
                    )
                    Text(text = "NAME " + it.name, modifier = Modifier.fillMaxWidth(.9f))
                }
                Row {
                    Text(
                        text = "AGE " + it.age.toString(),
                        modifier = Modifier.fillMaxWidth(.2f),
                        color = Color.Yellow
                    )
                    Text(text = "EMAIL " + it.email, modifier = Modifier.fillMaxWidth(.9f))
                }
            }
        }


    }

    @Composable
    private fun SqlButton(buttonName: String, function: () -> Unit) {
        Button(
            onClick = function, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
        ) {
            Text(
                text = buttonName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun LoginScreen() {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isRememberMeChecked by rememberSaveable { mutableStateOf(false) }
    var isPassword by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome Back",
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email
                ),

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email, contentDescription = null
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock, contentDescription = null
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        isPassword = !isPassword
                    }) {
                        Icon(
                            imageVector =
                            if (isPassword) Icons.Default.Lock else Icons.Default.LockOpen

                            , contentDescription = null
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Checkbox(
                    checked = isRememberMeChecked,
                    onCheckedChange = { isRememberMeChecked = it },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Remember me", style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    onClick = { /* TODO */ }, modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(text = "Forgot password?")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "Log In")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Don't have an account?", style = MaterialTheme.typography.body1, color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = { /* TODO */ }, modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Sign Up")
            }
        }
    }
}


