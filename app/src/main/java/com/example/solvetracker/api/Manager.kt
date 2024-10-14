package com.example.solvetracker.api

import com.example.solvetracker.api.model.UserInfo

suspend fun searchUser(query: String): List<UserInfo> {
    val client = UserClient.getInstance()
    val call = client.getUserListByName(1, query)
    val response = call.execute()

    if (response.isSuccessful)
        return response.body()?.items ?: emptyList()
    return emptyList()
}