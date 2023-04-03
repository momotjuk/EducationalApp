package com.educationalapp.core.di

import com.educationalapp.features.home.domain.useCase.GetUserUseCase
import com.educationalapp.features.home.domain.useCase.GetVideoUseCase
import com.educationalapp.features.home.domain.useCase.GetVideosUseCase
import com.educationalapp.features.home.presentation.HomeViewModel
import com.educationalapp.network.FirebaseAPI
import com.educationalapp.network.FirebaseRepository
import com.educationalapp.network.FirebaseRepositoryImpl
import com.google.firebase.database.FirebaseDatabase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val koinModule = module {
    single { FirebaseDatabase.getInstance() }
    single { FirebaseAPI(get()) }

    single<FirebaseRepository> { FirebaseRepositoryImpl(get()) }

    factory { GetUserUseCase(get(), get(named(DispatcherType.IO))) }
    factory { GetVideoUseCase(get(), get(named(DispatcherType.IO))) }
    factory { GetVideosUseCase(get(), get(named(DispatcherType.IO))) }

    viewModel { HomeViewModel(get(), get(), get()) }

}