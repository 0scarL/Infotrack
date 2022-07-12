package com.example.infotrack.data.net

import com.example.infotrack.data.model.Usuarios
import retrofit2.http.GET

interface UsuariosEndPoint {

    @GET("user")
    suspend fun getAllUsuarios() : Usuarios

}