package com.example.watchlinkapp.ComposeUI.User

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlinkapp.Entities.Model.User.User
import com.example.watchlinkapp.Entities.Repository.User.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    companion object {
        var userUiState by mutableStateOf(UserUiState())
            private set
    }
    fun login(userName: String, password: String, action: () -> Unit){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                userRepository.getAll()
                val user = userRepository.getUser(userName)
                if (user.password == password) {
                    withContext(Dispatchers.Main){
                        setAuthenticatedUser(user)
                        action()
                    }
                }
            }
        }
    }
    suspend fun registrationUser(user: User){
        userRepository.insert(user);
    }
    fun getAuthenticatedUser() : User{
        return userUiState.userDetails.toUser()
    }
    fun setAuthenticatedUser(user: User) {
        userUiState = user.toUiState()
    }
}
data class UserUiState(
    val userDetails: UserDetails = UserDetails()
)
data class UserDetails(
    val userId: Int? = 0,
    val userName: String = "",
    val dateOfBirth: String = "",
    val phoneNumber: String = "",
)
fun UserDetails.toUser(uid: Int = 0): User = User(
    userId = userId,
    userName = userName,
    dateOfBirth = dateOfBirth,
    phoneNumber = phoneNumber,
    password = ""
)

fun User.to_details(): UserDetails = UserDetails(
    userId = userId,
    userName = userName,
    dateOfBirth = dateOfBirth,
    phoneNumber = phoneNumber
)

fun User.toUiState(): UserUiState = UserUiState(
    userDetails = this.to_details()
)
