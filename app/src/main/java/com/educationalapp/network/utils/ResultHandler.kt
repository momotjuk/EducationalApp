package com.educationalapp.network.utils

import com.google.firebase.database.DataSnapshot

suspend fun <T> requestApiCall(
    call: suspend () -> DataSnapshot,
    responseModel: Class<T>
): Result<T> {
    return try {
        val response = call.invoke()
        response.getValue(responseModel)?.let { Result.Success(it) } ?: Result.Failure(
            Exception("Failed to parse model")
        )
    } catch (throwable: Throwable) {
        Result.Failure(Exception(throwable))
    }
}