package com.unlimit_test.domain.repository

import com.unlimit_test.data.model.JokeModel

interface JokesRepository {
    suspend fun getJokes():JokeModel?
}