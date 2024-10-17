package com.example.solvetracker.data.api.service

import com.example.solvetracker.data.model.ProblemList
import com.example.solvetracker.data.model.ProblemTag
import com.example.solvetracker.data.model.ProblemTagList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProblemService {
    @GET("search/problem")
    suspend fun getProblemList(
        @Query("page") page: Int,
        @Query("direction") direction: String = "desc",
        @Query("query") query: String,
        @Query("sort") sort: String = "level"
    ): Response<ProblemList>

    @GET("tag/show")
    suspend fun getTag(
        @Query("key") key: String
    ): Response<ProblemTag>

    @GET("tag/list")
    suspend fun getTagListAll(
        @Query("page") page: Int
    ): Response<ProblemTagList>

    @GET("search/tag")
    suspend fun getTagListByName(
        @Query("page") page: Int,
        @Query("query") query: String
    ): Response<ProblemTagList>
}