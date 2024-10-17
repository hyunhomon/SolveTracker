package com.example.solvetracker.data.repository

import com.example.solvetracker.data.api.RetrofitInstance
import com.example.solvetracker.data.api.service.UserService
import com.example.solvetracker.data.model.UserList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class UserRepository {
    private val userService: UserService by lazy {
        RetrofitInstance.retrofit.create(UserService::class.java)
    }

    fun searchUsers(query: String): Flow<Response<UserList>> = flow {
        val response = userService.getUserListByName(1, query)
        emit(response)
    }
}