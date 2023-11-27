package com.example.watchlinkapp.ComposeUI.User

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.watchlinkapp.ComposeUI.AppViewModelProvider
import com.example.watchlinkapp.ComposeUI.Movie.MovieViewModel
import com.example.watchlinkapp.ComposeUI.Navigation.Screen
import com.example.watchlinkapp.Database.AppDatabase
import com.example.watchlinkapp.Entities.Model.User.User
import com.example.watchlinkapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun Login(
    navController: NavController,
    viewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    fun onAuthenticationSuccess(){
        navController.navigate(Screen.MovieCatalog.route)
    }

    val coroutineScope = rememberCoroutineScope()

    fun login(){
        coroutineScope.launch() {
            withContext(Dispatchers.IO) {
                val user = viewModel.getUser(username)
                if (user != null) {
                    viewModel.setAuthenticatedUser(user)
                }
            }
            withContext(Dispatchers.Main) {
                onAuthenticationSuccess()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.backgroundColor)),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.login_title),
                fontSize = 26.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = username,
                onValueChange = { username = it },
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
                value = password,
                onValueChange = { password = it },
                label = { Text("Пароль") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
            Button(shape = RoundedCornerShape(5.dp),
                onClick = {
                        login()
                    },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.backgroundNavBarColor)
                )) {
                Text("Вход")
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = { navController.navigate(Screen.Signup.route) }) {
                Text(text = "У меня нет учетной записи")
            }
        }
    }
}