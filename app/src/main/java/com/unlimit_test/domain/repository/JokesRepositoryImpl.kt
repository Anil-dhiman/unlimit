package com.unlimit_test.domain.repository

import android.util.Log
import com.unlimit_test.data.api.JokesServices
import com.unlimit_test.data.model.JokeModel
import java.lang.Exception

class JokesRepositoryImpl( private val jokesService: JokesServices): JokesRepository {
    override suspend fun getJokes(): JokeModel? {
        lateinit var jokeModel : JokeModel
        try {
            val response = jokesService.getJokesApiCall()
            val body = response.body()
            if(body!=null){
                jokeModel = body
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return jokeModel
    }
}