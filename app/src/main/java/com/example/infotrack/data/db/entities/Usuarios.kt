package com.example.infotrack.data.db.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UsuariosEntity")
data class Usuarios(
    @PrimaryKey(autoGenerate = true)
    val Id: Int = 0,
    val aprobado: Boolean?,
    //val foto64 : String

)