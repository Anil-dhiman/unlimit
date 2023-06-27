package com.unlimit_test.presentation.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.unlimit_test.data.api.JokesServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

var appModule = module {

    /** PROVIDE GSON SINGLETON */
    single<Gson> {
        val builder = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        builder.setLenient().create()
    }

    /** PROVIDE RETROFIT SINGLETON */

    /** PROVIDE RETROFIT SINGLETON */

    single {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(loggingInterceptor)
        httpClient.connectTimeout(10000, TimeUnit.SECONDS)
        httpClient.readTimeout(10000, TimeUnit.SECONDS)
        httpClient.writeTimeout(10000, TimeUnit.SECONDS)
        httpClient.addInterceptor { chain ->
            val request = chain.request().newBuilder().build()
            chain.proceed(request)
        }

        val okHttpClient = httpClient.build()

        Retrofit.Builder()
            .baseUrl("https://geek-jokes.sameerkumar.website/")
            .addConverterFactory(GsonConverterFactory.create(get() as Gson))
            .client(okHttpClient)
            .build()
    }

    /*** Provide API Service Singleton*/
    single {
        (get<Retrofit>()).create<JokesServices>(JokesServices::class.java)
    }


}