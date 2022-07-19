package com.example.infotrack.domain

import com.example.infotrack.data.DbRepository
import com.example.infotrack.data.Repository
import com.example.infotrack.data.db.entities.Usuarios

class InsertUsuarioDBCase {

    private val dBrepository = DbRepository()

    operator suspend fun invoke (usuarios: Usuarios){
        dBrepository.insertUsuario(usuarios)
    }

}