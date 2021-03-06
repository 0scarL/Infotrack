package com.example.infotrack.data.net

import com.example.infotrack.data.model.Usuarios
import com.example.infotrack.data.utils.RetrofitCaller
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsuariosService {

    suspend fun getUsuarios(): List<Usuarios> {
        return withContext(Dispatchers.IO){
            val response = RetrofitCaller.service.getAllUsuarios()
            response?: emptyList()
        }

    }
}