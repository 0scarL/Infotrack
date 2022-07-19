package com.example.infotrack.domain

import com.example.infotrack.data.DbRepository
import com.example.infotrack.data.Repository
import com.example.infotrack.data.db.entities.Usuarios

class GetUsuariosFromDbCase {
    private var dBrepository = DbRepository()

    suspend operator fun invoke(): List<Usuarios>{
        return dBrepository.getUsuariosFromDb()
    }
}