package com.example.solvetracker.api

import com.example.solvetracker.api.service.ProblemService
import com.example.solvetracker.api.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://solved.ac/api/v3/"

object UserClient {
    private var instance: UserService? = null

    fun getInstance(): UserService {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService::class.java)
        }
        return instance!!
    }
}

object ProblemClient {
    private var instance: ProblemService? = null

    fun getInstance(): ProblemService {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProblemService::class.java)
        }
        return instance!!
    }
}