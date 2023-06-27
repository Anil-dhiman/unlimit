package com.unlimit_test.domain.usecase

import com.unlimit_test.data.model.JokeModel
import com.unlimit_test.domain.repository.JokesRepository

class JokesUseCase(private val jokesRepository: JokesRepository) {
    suspend fun execute():JokeModel? = jokesRepository.getJokes()
}