package com.example.progmob2023.model

import com.google.gson.annotations.SerializedName

data class ResponseMhs(

    @field:SerializedName("data")
    val data: List<MhsDataItem?>? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class MhsDataItem(
    @field:SerializedName("nama")
    var nama: String? = null,

    @field:SerializedName("nim")
    var nim: String? = null,

    @field:SerializedName("alamat")
    var alamat: String? = null,

    @field:SerializedName("email")
    var email: String? = null,

    @field:SerializedName("foto")
    var foto: String? = null,

    @field:SerializedName("id")
    var id: String? = null
)
