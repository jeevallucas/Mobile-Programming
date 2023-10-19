package com.example.progmob2023.network

import com.example.progmob2023.model.DataItem
import com.example.progmob2023.model.ResponseUsersItem
import com.example.progmob2023.model.ResponseMhs
import com.example.progmob2023.model.MhsDataItem
import com.example.progmob2023.model.ResponseAddMhs
import com.example.progmob2023.model.ResponseAddPetani
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class NetworkConfigMhs {
    fun getInterceptorMhs() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return okHttpClient
    }

    fun getRetrofitMhs() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://kpsi.fti.ukdw.ac.id/")
            .client(getInterceptorMhs())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getServiceMhs() = getRetrofitMhs().create(UsersMhs::class.java)
}

interface UsersMhs {
    @GET("users")
    fun getUsers(): Call<List<ResponseUsersItem>>

    @GET("api/progmob/mhs/{nim_progmob}")
    fun getMhs(@Path("nim_progmob") nim: String,
               @Body req : MhsDataItem): Call<ResponseMhs>

    @GET("api/progmob/mhs/{nim_progmob}/{nim_mhs}")
    fun getSpesifikMhs(@Path("nim_progmob") nim: String,
                       @Path("nim_mhs") nimMhs: String,
                       @Body req : MhsDataItem): Call<ResponseMhs>

    @POST("api/progmob/mhs/create")
    fun addMhs(@Body req: MhsDataItem): Call<ResponseAddMhs>

    @POST("api/progmob/mhs/update")
    fun updateMhs(@Body req: MhsDataItem): Call<ResponseAddMhs>

    @POST("api/progmob/mhs/delete")
    fun deleteMhs(@Body req: Int): Call<ResponseAddMhs>
}
