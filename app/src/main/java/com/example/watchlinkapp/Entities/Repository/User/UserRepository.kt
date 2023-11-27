package com.example.watchlinkapp.Entities.Repository.User

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.watchlinkapp.Entities.Model.User.User

interface UserRepository {
    fun getAll(): List<User>
    fun getUser(userName: String): User
    suspend fun insert(user: User)
    suspend fun update(user: User)
    suspend fun delete(user: User)
}