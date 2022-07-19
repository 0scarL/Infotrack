package com.example.infotrack.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Observer
import com.example.infotrack.data.Camara
import com.example.infotrack.data.db.entities.Usuarios
import com.example.infotrack.data.utils.Sumas
import com.example.infotrack.data.utils.UsuarioCarrier
import com.example.infotrack.databinding.ActivityApruebaInfoBinding

import com.example.infotrack.viewModel.ApruebaInfoViewModel
import java.io.ByteArrayOutputStream

class ApruebaInfo : AppCompatActivity() {
    private lateinit var binding: ActivityApruebaInfoBinding
    private val apruebaInfoViewModel: ApruebaInfoViewModel by viewModels()
    private val camara by lazy { Camara(this) }
    private var fotoReady = false


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityApruebaInfoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.checkbox.setOnClickListener {
            if (binding.checkbox.isChecked) {
                apruebaInfoViewModel.checked()
                apruebaInfoViewModel.currentProgress.observe(this,
                    Observer { currentProgress -> binding.progresBar.setProgress(currentProgress) })
            }
            if (!binding.checkbox.isChecked) {
                apruebaInfoViewModel.unChek()
                apruebaInfoViewModel.currentProgress.observe(this,
                    Observer { currentProgress -> binding.progresBar.setProgress(currentProgress) })
            }
        }


        binding.openCamera.setOnClickListener {
            if (binding.checkbox.isChecked)
                camara.lanzarCamara()
            else {
                Toast.makeText(this, "Por favor Acepte Tratamiento Datos", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.photoTaken.setOnClickListener {
            if (fotoReady && (Sumas.getTermino() == 100)) {
                apruebaInfoViewModel.insertUsuario()
                Toast.makeText(this, "Informacion Guardada", Toast.LENGTH_SHORT).show()
                apruebaInfoViewModel.finaliza()
                apruebaInfoViewModel.currentProgress.observe(this,
                    Observer { currentProgress ->
                        binding.progresBar.setProgress(currentProgress)

                    })
                Log.d("obtener foto base 64", UsuarioCarrier.foto64)
                finish()

            }
        }

        binding.tvAprobacion.setOnClickListener {
           // apruebaInfoViewModel.insertUsuario()
            apruebaInfoViewModel.getUsuariosDB()
            apruebaInfoViewModel.userFromDB.observe(this, Observer { userFromdb ->
                if (!userFromdb.isNullOrEmpty()) {
                    Log.d("Aprueba Activity", "datos from db: " + userFromdb.size)
                    Toast.makeText(this, "Usuarios En DB: "+userFromdb.size, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Camara.REQUEST_CODE && resultCode == RESULT_OK) {
            var imgBitmap: Bitmap? = BitmapFactory.decodeFile(Camara.rutaImagen)
            //saveBitmap(imgBitmap)
            binding.photoTaken.setImageBitmap(imgBitmap)
            binding.photoTaken.setRotation(90F)
            Toast.makeText(this, "Informacion Lista Para Enviar", Toast.LENGTH_LONG).show()
            fotoReady = true
            UsuarioCarrier.foto64 = get64base()
            createUser()
            apruebaInfoViewModel.checked()
            //apruebaInfoViewModel.insertUsuario()


        }

    }

    private fun createUser() {
        UsuarioCarrier.user = Usuarios(UsuarioCarrier.unUsuario.id,
            fotoReady)
        Log.d("create user", UsuarioCarrier.user.toString())
    }

private fun get64base(): String {
    val drawable: Drawable = binding.photoTaken.drawable
    val bitmap: Bitmap = drawable.toBitmap()
    val byteArrayOut = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOut)
    var byteArray = byteArrayOut.toByteArray()
    val foto64 = Base64.encodeToString(byteArray, Base64.DEFAULT)
    //Log.d("obtener bitmap", foto64)
    return foto64 ?: "No se pudo codificar la foto"

}



}
