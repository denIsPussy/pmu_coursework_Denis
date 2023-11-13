package com.example.watchlinkapp.ComposeUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.watchlinkapp.Database.AppDatabase
import com.example.watchlinkapp.Entities.Model.AuthModel
import com.example.watchlinkapp.Entities.Model.User
import com.example.watchlinkapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun Profile(){
    var phoneNumber by remember { mutableStateOf("Ваш номер телефона") }
    var userName by remember { mutableStateOf("Имя пользователя") }
    var dateOfBirth by remember { mutableStateOf("01-01-2000") }
    val (user, setUser) = remember { mutableStateOf<User?>(null) }
    val context = LocalContext.current
    //val authModel: AuthModel = viewModel()
    val database = remember { AppDatabase.getInstance(context) }

    fun ini(){
        val currentUser = AuthModel.currentUser
        if (currentUser != null) {
            setUser(AppDatabase.getInstance(context).userDao().getUser(currentUser.userName))
        }
    }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val currentUser = AuthModel.currentUser
            if (currentUser != null) {
                setUser(AppDatabase.getInstance(context).userDao().getUser(currentUser.userName))
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.backgroundColor)),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        user?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .padding(bottom = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.drawable.icon_profile), contentDescription = "", Modifier.size(140.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Профиль",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = user.userName,
                    onValueChange = { userName = it },
                    label = { Text("Имя пользователя") },
                    colors = TextFieldDefaults.colors(
                        unfocusedLabelColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                        unfocusedTextColor = colorResource(id = R.color.button),
                        focusedTextColor = colorResource(id = R.color.button),
                        unfocusedContainerColor = colorResource(id = R.color.backgroundNavBarColor),
                        focusedContainerColor = colorResource(id = R.color.backgroundNavBarColor)
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = user.dateOfBirth,
                    onValueChange = { dateOfBirth = it },
                    label = { Text("Дата рождения") },
                    colors = TextFieldDefaults.colors(
                        unfocusedLabelColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                        unfocusedTextColor = colorResource(id = R.color.button),
                        focusedTextColor = colorResource(id = R.color.button),
                        unfocusedContainerColor = colorResource(id = R.color.backgroundNavBarColor),
                        focusedContainerColor = colorResource(id = R.color.backgroundNavBarColor)
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = user.phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Номер телефона") },
                    colors = TextFieldDefaults.colors(
                        unfocusedLabelColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                        unfocusedTextColor = colorResource(id = R.color.button),
                        focusedTextColor = colorResource(id = R.color.button),
                        unfocusedContainerColor = colorResource(id = R.color.backgroundNavBarColor),
                        focusedContainerColor = colorResource(id = R.color.backgroundNavBarColor)
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    shape = RoundedCornerShape(5.dp),
                    onClick = { /* Handle saving data */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.backgroundNavBarColor)
                    )
                ) {
                    Text("Сохранить")
                }
            }
        }
    }

}