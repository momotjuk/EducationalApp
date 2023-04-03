package com.educationalapp.network.model.repository

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("username")
    val username: String = "",
    @SerializedName("firstName")
    val firstName: String = "",
    @SerializedName("lastName")
    val lastName: String = ""
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "username" to username,
            "first_name" to firstName,
            "last_name" to lastName
        )
    }
}

