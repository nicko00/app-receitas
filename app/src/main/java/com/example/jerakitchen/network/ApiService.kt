package com.example.jerakitchen.network

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService() {

    private val okHttpClient = OkHttpClient().newBuilder().addInterceptor { chain ->
        val mRequest: Request = chain.request()
        val builder: Request.Builder = mRequest.newBuilder()
            .header("X-RapidAPI-Host", "tasty.p.rapidapi.com")
            .addHeader("X-RapidAPI-Key", "839768c1acmsh4a0609412a28f21p1129eajsndb5024710672")
        val request: Request = builder.build()
        chain.proceed(request)
    }.build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://tasty.p.rapidapi.com")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun recipeService() : RecipeService = this.retrofit.create(RecipeService::class.java)
}
