package com.example.watchlinkapp.Entities.Repository.User

import com.example.watchlinkapp.Entities.DAO.User.UserDAO
import com.example.watchlinkapp.Entities.Model.User.User

class OfflineUserRepository(private val userDAO: UserDAO) : UserRepository {
    override fun getAll(): List<User> = userDAO.getAll()

    override fun getUser(userName: String): User = userDAO.getUser(userName)

    override suspend fun insert(user: User) = userDAO.insert(user)

    override suspend fun update(user: User) = userDAO.update(user)

    override suspend fun delete(user: User) = userDAO.delete(user)
}