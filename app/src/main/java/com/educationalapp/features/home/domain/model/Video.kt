package com.educationalapp.features.home.domain.model

import com.educationalapp.features.home.logic.Like

data class Video(
    val id: Long,
    val name: String,
    var videoURL: String,
    val likeStatus: Like?,
    val likedCount: Long,
    val dislikedCount: Long
)
