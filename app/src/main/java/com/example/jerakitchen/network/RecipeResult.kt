package com.example.jerakitchen.network

import com.example.jerakitchen.model.Recipe
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecipeResult (
    @SerializedName("results")
    val recipeResult : List<Recipe>
) : Serializable
