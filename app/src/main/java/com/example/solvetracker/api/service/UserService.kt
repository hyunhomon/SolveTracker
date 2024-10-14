package com.example.solvetracker.api.service

import com.example.solvetracker.api.model.ProblemSolvedClass
import com.example.solvetracker.api.model.ProblemSolvedTagList
import com.example.solvetracker.api.model.ProblemSolvedTier
import com.example.solvetracker.api.model.UserAdditionalInfo
import com.example.solvetracker.api.model.UserBackground
import com.example.solvetracker.api.model.UserHistory
import com.example.solvetracker.api.model.UserInfo
import com.example.solvetracker.api.model.UserList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("user/show")
    suspend fun getUserInfo(
        @Query("handle") handle: String
    ): Call<UserInfo>

    @GET("user/additional_info")
    suspend fun getUserAdditionalInfo(
        @Query("handle") handle: String
    ): Call<UserAdditionalInfo>

    @GET("background/show")
    suspend fun getUserBackground(
        @Query("backgroundId") backgroundId: String
    ): Call<UserBackground>

    @GET("user/history")
    suspend fun getUserHistory(
        @Query("handle") handle: String,
        @Query("topic") topic: String = "solvedCount"
    ): Call<List<UserHistory>>

    @GET("user/problem_stats")
    suspend fun getUserSolvedTier(
        @Query("handle") handle: String
    ): Call<List<ProblemSolvedTier>>

    @GET("user/problem_tag_stats")
    suspend fun getUserSolvedTag(
        @Query("handle") handle: String
    ): Call<ProblemSolvedTagList>

    @GET("user/class_stats")
    suspend fun getUserSolvedClass(
        @Query("handle") handle: String
    ): Call<List<ProblemSolvedClass>>

    @GET("search/user")
    suspend fun getUserListByName(
        @Query("page") page: Int,
        @Query("query") query: String
    ): Call<UserList>

    @GET("ranking/tier")
    suspend fun getUserListByRanking(
        @Query("page") page: Int
    ): Call<UserList>

    @GET("ranking/streak")
    suspend fun getUserListByStreak(
        @Query("page") page: Int
    ): Call<UserList>
}