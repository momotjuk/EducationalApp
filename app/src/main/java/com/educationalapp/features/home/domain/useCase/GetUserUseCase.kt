package com.educationalapp.features.home.domain.useCase

import com.educationalapp.network.model.repository.UserResponse
import com.educationalapp.network.FirebaseRepository
import com.educationalapp.network.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetUserUseCase(
    private val repository: FirebaseRepository,
    private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(
        id: String?
    ): Result<UserResponse> =
        withContext(dispatcher) {
            return@withContext id?.let { repository.getUser(id) }
                ?: Result.Failure(Exception("User id is empty"))
        }
}