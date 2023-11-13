package com.example.watchlinkapp.Entities.Model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthModel : ViewModel() {
    companion object {
        lateinit var currentUser: User
            private set
    }

    fun setAuthenticatedUser(user: User?) {
        currentUser = user ?: throw IllegalArgumentException("User cannot be null")
    }
}