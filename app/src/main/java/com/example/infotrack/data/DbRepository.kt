package com.example.infotrack.data

import android.util.Log
import com.example.infotrack.data.db.entities.Usuarios
import com.example.infotrack.view.UsuariosApplication
import com.example.infotrack.view.UsuariosApplication.Companion.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DbRepository {

    suspend fun insertUsuario(usuarios: Usuarios){
        withContext(Dispatchers.IO){
            database.usuariosDao().insertUser(usuarios)
            Log.d("dbrepository insertando",usuarios.Id.toString())
        }
    }

    suspend fun getUsuariosFromDb() : List<Usuarios>{
        return withContext(Dispatchers.IO){
            database.usuariosDao().getUsuariosFromDB()
        }
    }
}