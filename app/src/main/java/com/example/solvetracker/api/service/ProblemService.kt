package com.example.solvetracker.api.service

import com.example.solvetracker.api.model.ProblemList
import com.example.solvetracker.api.model.ProblemTag
import com.example.solvetracker.api.model.ProblemTagList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProblemService {
    @GET("search/problem")
    suspend fun getProblemList(
        @Query("page") page: Int,
        @Query("direction") direction: String = "desc",
        @Query("query") query: String,
        @Query("sort") sort: String = "level"
    ): Call<ProblemList>

    @GET("tag/show")
    suspend fun getTag(
        @Query("key") key: String
    ): Call<ProblemTag>

    @GET("tag/list")
    suspend fun getTagListAll(
        @Query("page") page: Int
    ): Call<ProblemTagList>

    @GET("search/tag")
    suspend fun getTagListByName(
        @Query("page") page: Int,
        @Query("query") query: String
    ): Call<ProblemTagList>
}