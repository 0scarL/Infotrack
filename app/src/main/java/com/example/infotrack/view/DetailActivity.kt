package com.example.infotrack.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.example.infotrack.data.Camara
import com.example.infotrack.data.Camara.Companion.REQUEST_CODE
import com.example.infotrack.data.Camara.Companion.rutaImagen
import com.example.infotrack.data.model.Usuarios
import com.example.infotrack.data.utils.UsuarioCarrier
import com.example.infotrack.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    private var fotoReady = false

    private val camara by lazy { Camara(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("Detail Activity", "llega dato" + UsuarioCarrier.unUsuario)
        postearOnScreen()

        binding.openCamera.setOnClickListener {
            camara.lanzarCamara()
        }

        binding.photoTaken.setOnClickListener {
            if (fotoReady) navegateTo(UsuarioCarrier.unUsuario)
        }
        Log.d("Detail Activity", fotoReady.toString())

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            //val extras: Bundle? = data?.extras
            //val imgBitmap: Bitmap? = extras?.get("data") as Bitmap
            val imgBitmap: Bitmap? = BitmapFactory.decodeFile(rutaImagen)
            binding.photoTaken.setImageBitmap(imgBitmap)
            binding.photoTaken.setRotation(90F)
            Toast.makeText(this, "Foto almacenada en la APP", Toast.LENGTH_SHORT).show()
            fotoReady = true

        }

    }

    private fun navegateTo(usuarios: Usuarios) {
        val intent = Intent(this, ApruebaInfo::class.java)
        UsuarioCarrier.unUsuario = usuarios
        startActivity(intent)
        finish()

    }

    private fun postearOnScreen() {
        UsuarioCarrier.unUsuario.let { usuario ->

            binding.tvDetailInfo.text = buildSpannedString {

                bold { append("Nombre de Usuario: ") }
                if (usuario.name != null) appendLine(usuario.name)

                bold { append("User Name: ") }
                if (usuario.username != null)
                    appendLine(usuario.username)

                bold { append("Ciudad : ") }
                if (usuario.address.city != null) appendLine(usuario.address.city)
            }

            binding.tvDetailCompany.text = buildSpannedString {

                bold { append("Nombre de Compañía: ") }
                if (usuario.company.name != null) appendLine(usuario.company.name)

                bold { append("BS: ") }
                if (usuario.company.bs != null)
                    appendLine(usuario.company.bs)

                bold { append("Eslogan o Catchphrase: ") }
                if (usuario.company.catchPhrase != null) appendLine(usuario.company.catchPhrase)
            }
        }

    }


}