package com.example.suitmediatest.data.repository

import android.util.Base64
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.suitmediatest.data.model.GuestModel
import retrofit2.Callback
import com.example.suitmediatest.data.retrofit.APIClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import java.nio.charset.StandardCharsets

object GuestRepository {

fun getGuest(page: Int): MutableLiveData<GuestModel> {

    val Guest = MutableLiveData<GuestModel>()


    APIClient.API_SERVICES.getGuest(page.toString()).enqueue(object : Callback<GuestModel> {
        override fun onResponse(call: Call<GuestModel>, response: Response<GuestModel>) {
            Guest.value = response.body()

        }

        override fun onFailure(call: Call<GuestModel>, t: Throwable) {
            Log.d("error", "Retrofit error")
        }

    })

    return Guest
}

}
