package com.unlimit_test.presentation.di

import com.unlimit_test.domain.repository.JokesRepository
import com.unlimit_test.domain.repository.JokesRepositoryImpl
import com.unlimit_test.domain.usecase.JokesUseCase
import org.koin.dsl.module

var repoModule = module {

    single<JokesRepository> { JokesRepositoryImpl(get()) }
    single { JokesUseCase(get()) }
    single { JokesRepositoryImpl(get()) }
}