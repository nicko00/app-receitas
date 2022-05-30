package com.example.jerakitchen.network

import retrofit2.Call
import com.example.jerakitchen.model.Recipe
import retrofit2.http.GET
import retrofit2.http.Query

    interface RecipeService {
        @GET("/recipes/list?from=0&size=20")
        fun getRecipes(
            @Query("page")
            page: Int
        ): Call<RecipeResult>
    }
