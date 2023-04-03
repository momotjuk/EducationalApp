package com.educationalapp.features.home.presentation

import androidx.lifecycle.LiveData
import com.educationalapp.core.presentation.BaseViewModel
import com.educationalapp.core.presentation.UiState
import com.educationalapp.core.utils.SingleLiveEvent
import com.educationalapp.network.model.repository.UserResponse
import com.educationalapp.features.home.domain.useCase.GetUserUseCase
import com.educationalapp.features.home.domain.useCase.GetVideoUseCase
import com.educationalapp.features.home.domain.useCase.GetVideosUseCase
import com.educationalapp.network.model.repository.Video
import com.educationalapp.network.model.repository.VideoResponse
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val getVideoUseCase: GetVideoUseCase,
    private val getVideosUseCase: GetVideosUseCase
    ) : BaseViewModel() {

    private val _getUserResponse = SingleLiveEvent<UiState<UserResponse>>()
    private val _getVideoResponse = SingleLiveEvent<UiState<Video>>()
    private val _getVideosResponse = SingleLiveEvent<UiState<VideoResponse>>()
    val getUserResponse: LiveData<UiState<UserResponse>> = _getUserResponse
    val getVideoResponse: LiveData<UiState<Video>> = _getVideoResponse
    val getVideosResponse: LiveData<UiState<VideoResponse>> = _getVideosResponse

    private val id: String? = FirebaseAuth.getInstance().currentUser?.uid

    fun getUser() {
        launchWithUIState(
            block = { getUserUseCase.invoke(id) },
            liveData = _getUserResponse
        )
    }

    fun getVideo() {
        launchWithUIState(
            block = { getVideoUseCase.invoke("1") },
            liveData = _getVideoResponse
        )
    }

    fun getVideos() {
        launchWithUIState(
            block = { getVideosUseCase.invoke() },
            liveData = _getVideosResponse
        )
    }
}