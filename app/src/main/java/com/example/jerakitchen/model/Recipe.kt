package com.example.jerakitchen.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Recipe (
    @SerializedName("name")
    val name : String?,
    @SerializedName("thumbnail_url")
    val thumbnail : String?,
    @SerializedName("total_time_minutes")
    val cookTime: String?,
    @SerializedName("id")
    val id : Int?
) : Serializable