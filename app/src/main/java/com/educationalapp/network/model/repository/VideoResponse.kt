package com.educationalapp.network.model.repository

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("videos")
    val video: List<Video?>
)
