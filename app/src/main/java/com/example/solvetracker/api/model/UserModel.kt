package com.example.solvetracker.api.model

data class UserInfo(
    val handle: String,
    val bio: String,
    val backgroundId: String,
    val profileImageUrl: String?,
    val solvedCount: Int,
    val rivalCount: Int,
    val reverseRivalCount: Int,
    val tier: Int,
    val rating: Int,
    val maxStreak: Int,
    val rank: Int
)

data class UserAdditionalInfo(
    val countryCode: String,
    val pronouns: String,
    val birthYear: Int,
    val birthMonth: Int,
    val birthDay: Int,
    val name: String,
    val nameNative: String
)

data class UserBackground(
    val backgroundId: String,
    val backgroundImageUrl: String,
    val fallbackBackgroundImageUrl: String?,
    val backgroundVideoUrl: String?,
    val unlockedUserCount: Int,
    val displayName: String,
    val displayDescription: String,
    val conditions: String?,
    val hiddenConditions: Boolean,
    val isIllust: Boolean
)

data class UserHistory(
    val timestamp: String,
    val value: Int
)

data class UserList(
    val count: Int,
    val items: List<UserInfo>
)