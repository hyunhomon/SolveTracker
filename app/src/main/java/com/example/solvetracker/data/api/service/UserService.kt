package com.example.solvetracker.data.api.service

import com.example.solvetracker.data.model.ProblemSolvedClass
import com.example.solvetracker.data.model.ProblemSolvedTagList
import com.example.solvetracker.data.model.ProblemSolvedTier
import com.example.solvetracker.data.model.UserAdditionalInfo
import com.example.solvetracker.data.model.UserBackground
import com.example.solvetracker.data.model.UserHistory
import com.example.solvetracker.data.model.UserInfo
import com.example.solvetracker.data.model.UserList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("user/show")
    suspend fun getUserInfo(
        @Query("handle") handle: String
    ): Response<UserInfo>

    @GET("user/additional_info")
    suspend fun getUserAdditionalInfo(
        @Query("handle") handle: String
    ): Response<UserAdditionalInfo>

    @GET("background/show")
    suspend fun getUserBackground(
        @Query("backgroundId") backgroundId: String
    ): Response<UserBackground>

    @GET("user/history")
    suspend fun getUserHistory(
        @Query("handle") handle: String,
        @Query("topic") topic: String = "solvedCount"
    ): Response<List<UserHistory>>

    @GET("user/problem_stats")
    suspend fun getUserSolvedTier(
        @Query("handle") handle: String
    ): Response<List<ProblemSolvedTier>>

    @GET("user/problem_tag_stats")
    suspend fun getUserSolvedTag(
        @Query("handle") handle: String
    ): Response<ProblemSolvedTagList>

    @GET("user/class_stats")
    suspend fun getUserSolvedClass(
        @Query("handle") handle: String
    ): Response<List<ProblemSolvedClass>>

    @GET("search/user")
    suspend fun getUserListByName(
        @Query("page") page: Int,
        @Query("query") query: String
    ): Response<UserList>

    @GET("ranking/tier")
    suspend fun getUserListByRanking(
        @Query("page") page: Int
    ): Response<UserList>

    @GET("ranking/streak")
    suspend fun getUserListByStreak(
        @Query("page") page: Int
    ): Response<UserList>
}