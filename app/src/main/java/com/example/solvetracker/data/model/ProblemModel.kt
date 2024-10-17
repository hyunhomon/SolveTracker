package com.example.solvetracker.data.model

import com.google.gson.annotations.SerializedName

data class Problem(
    val problemId: Int,
    val titleKo: String,
    val isSolveable: Boolean,
    val isPartial: Boolean,
    val acceptedUserCount: Int,
    val level: Int,
    val sprout: Boolean,
    val givesNoRating: Boolean,
    val averageTries: Double,
    val official: Boolean,
    val tags: List<ProblemTag>
)

data class ProblemList(
    val count: Int,
    val items: List<Problem>
)

data class ProblemTagDisplayName(
    val language: String,
    val name: String
)

data class ProblemTag(
    val key: String,
    val isMeta: Boolean,
    val bojTagId: Int,
    val problemCount: Int,
    val displayNames: List<ProblemTagDisplayName>
)

data class ProblemTagList(
    val count: Int,
    val items: List<ProblemTag>
)

data class ProblemSolvedTier(
    val level: Int,
    val total: Int,
    val solved: Int,
    val partial: Int,
    val tried: Int
)

data class ProblemSolvedTag(
    val tag: ProblemTag,
    val total: Int,
    val solved: Int,
    val partial: Int,
    val tried: Int
)

data class ProblemSolvedTagList(
    val count: Int,
    val items: List<ProblemSolvedTag>
)

data class ProblemSolvedClass(
    @SerializedName("class")
    val classNum: Int,
    val total: Int,
    val totalSolved: Int,
    val decoration: String
)