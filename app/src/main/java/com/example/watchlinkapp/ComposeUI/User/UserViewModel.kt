package com.example.watchlinkapp.ComposeUI.User

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlinkapp.ComposeUI.Movie.toUiState
import com.example.watchlinkapp.Entities.Model.User.User
import com.example.watchlinkapp.Entities.Repository.User.UserRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    companion object {
        var userUiState by mutableStateOf(UserUiState())
            private set
    }
    fun getUser(userName: String) : User?{
        var user: User? = null
        user = userRepository.getUser(userName);
        return user
    }
    suspend fun registrationUser(user: User){
        userRepository.insert(user);
    }
    fun getAuthenticatedUser() : User{
        return userUiState.userDetails.toUser()
    }
    fun setAuthenticatedUser(user: User) : Boolean {
//        var user = getUser(userName);
//        if (user.password != password) return false
        userUiState = user.toUiState()
        return true
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
