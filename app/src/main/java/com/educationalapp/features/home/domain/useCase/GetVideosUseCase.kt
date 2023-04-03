package com.educationalapp.features.home.domain.useCase

import com.educationalapp.network.FirebaseRepository
import com.educationalapp.network.model.repository.VideoResponse
import com.educationalapp.network.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetVideosUseCase(
    private val repository: FirebaseRepository,
    private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(): Result<VideoResponse> =
        withContext(dispatcher) {
            return@withContext repository.getVideos()
        }

}