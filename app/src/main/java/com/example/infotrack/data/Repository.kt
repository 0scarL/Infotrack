package com.example.infotrack.data

import com.example.infotrack.data.model.Usuarios
import com.example.infotrack.data.net.UsuariosService


class Repository {

    private val usuariosService = UsuariosService()

    suspend fun getAllUsuarios(): List<Usuarios> {
        val respuesta = usuariosService.getUsuarios()
        return respuesta
    }



}