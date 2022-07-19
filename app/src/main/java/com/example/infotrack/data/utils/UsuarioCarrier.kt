package com.example.infotrack.data.utils

import android.graphics.Bitmap
import com.example.infotrack.data.model.Usuarios

object UsuarioCarrier {

    lateinit var unUsuario: Usuarios
    lateinit var usuarios: List<Usuarios>
    lateinit var user : com.example.infotrack.data.db.entities.Usuarios
    lateinit var foto64: String
    lateinit var bitmap: Bitmap
    var aprobacion = false


}