package com.example.progmob2023.model

import com.google.gson.annotations.SerializedName

data class ResponseAddPetani(

    @field:SerializedName("data")
    val data: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)