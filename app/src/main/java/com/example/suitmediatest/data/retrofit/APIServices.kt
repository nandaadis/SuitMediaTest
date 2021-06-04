package com.example.suitmediatest.data.retrofit

import com.example.suitmediatest.data.model.GuestModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {

    @GET("users")
    fun getGuest(@Query("page") page: String?): Call<GuestModel>


//    @GET("users")
//    fun getGuest(): Call<GuestModel>


}