package com.educationalapp.features.video.logic

data class Comment(
    val id: Long,
    val videoId: Long,
    val timestamp: Long,
    val userId: Long,
    val text: String
)
