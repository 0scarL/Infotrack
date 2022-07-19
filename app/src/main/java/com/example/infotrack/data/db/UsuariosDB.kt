package com.example.infotrack.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.infotrack.data.db.entities.Usuarios
import com.example.infotrack.data.db.entities.UsuariosDao


@Database(entities = [Usuarios::class], version = 1, exportSchema = false)
abstract class UsuariosDB: RoomDatabase() {

abstract fun usuariosDao(): UsuariosDao
}