package com.educationalapp.network

import com.educationalapp.network.model.repository.UserResponse
import com.educationalapp.network.model.repository.Video
import com.educationalapp.network.model.repository.VideoResponse
import com.educationalapp.network.utils.Result
import com.educationalapp.network.utils.requestApiCall

class FirebaseRepositoryImpl(private val firebaseAPI: FirebaseAPI) : FirebaseRepository {

    override suspend fun getUser(id: String): Result<UserResponse> {
        return requestApiCall(
            call = { firebaseAPI.getUser(id) },
            responseModel = UserResponse::class.java
        )
    }

    override suspend fun getVideo(id: String): Result<Video> {
        return requestApiCall(
            call = { firebaseAPI.getVideo(id) },
            responseModel = Video::class.java
        )
    }

    override suspend fun getVideos(): Result<VideoResponse> {
        return requestApiCall(
            call = { firebaseAPI.getVideos() },
            responseModel = VideoResponse::class.java
        )
    }
}