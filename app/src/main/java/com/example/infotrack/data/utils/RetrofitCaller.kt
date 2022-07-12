package com.example.infotrack.data.utils

import com.example.infotrack.data.net.UsuariosEndPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCaller {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service = getRetrofit().create(UsuariosEndPoint::class.java)

}