package com.example.infotrack.domain

import com.example.infotrack.data.Repository
import com.example.infotrack.data.model.Usuarios

class GetUsuariosUseCase {

    private var repository = Repository()

    suspend operator fun invoke(): List<Usuarios>{
        return repository.getAllUsuarios()
    }

}