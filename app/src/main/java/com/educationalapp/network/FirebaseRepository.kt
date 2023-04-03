package com.educationalapp.network

import com.educationalapp.network.model.repository.UserResponse
import com.educationalapp.network.model.repository.Video
import com.educationalapp.network.model.repository.VideoResponse
import com.educationalapp.network.utils.Result

interface FirebaseRepository {
    suspend fun getUser(id: String): Result<UserResponse>
    suspend fun getVideo(id: String): Result<Video>
    suspend fun getVideos(): Result<VideoResponse>
}