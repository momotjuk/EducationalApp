package com.educationalapp.network.model.repository

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("likedIds")
    val lickedIds: String? = "",
    @SerializedName("dislikedIds")
    val dislikedIds: String? = "",
    @SerializedName("url")
    val url: String? = ""
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "name" to name,
            "likedIds" to lickedIds,
            "dislikedIds" to dislikedIds,
            "url" to url
        )
    }

//    constructor(value: Map<String, Any?>) : this (
//        value["id"] as Int,
//        value["name"] as String,
//        value["likedIds"] as String,
//        value["dislikedIds"] as String,
//        value["url"] as String
//    )
}
