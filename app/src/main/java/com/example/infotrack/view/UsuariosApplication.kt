package com.example.infotrack.view

import android.app.Application
import androidx.room.Room
import com.example.infotrack.data.db.UsuariosDB

class UsuariosApplication : Application() {


    companion object {
        lateinit var database: UsuariosDB
    }

    override fun onCreate() {
        super.onCreate()
        database =
            Room.databaseBuilder(this, UsuariosDB::class.java, "usuarios-db").build()
    }
}