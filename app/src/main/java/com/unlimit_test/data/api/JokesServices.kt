package com.unlimit_test.data.api

import com.unlimit_test.data.model.JokeModel
import retrofit2.Response
import retrofit2.http.GET

interface JokesServices {

    @GET("api?format=json")
    suspend fun getJokesApiCall(): Response<JokeModel>

}