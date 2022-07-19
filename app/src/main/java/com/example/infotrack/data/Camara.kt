package com.example.infotrack.data

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.infotrack.BuildConfig
import java.io.File

class Camara(
    val activity: Context,

    ) {

    companion object {
        var rutaImagen: String = ""
        val REQUEST_CODE = 1
    }

    fun lanzarCamara() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(activity.packageManager) != null) {
            var imagenArhivo: File? = null
            try {
                imagenArhivo = createImagen()
            } catch (e: Exception) {
                Toast.makeText(activity, "Error procesando imagen", Toast.LENGTH_SHORT).show()
            }
            if (imagenArhivo != null) {
                val fotoUri: Uri = FileProvider.getUriForFile(
                    activity, BuildConfig.APPLICATION_ID + ".fileprovider", imagenArhivo)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri)
                val a = activity as Activity
                a.startActivityForResult(intent, REQUEST_CODE)

            }
        }
    }


    private fun createImagen(): File? {
        val nombreImagen = "foto_"
        val directiorio: File? = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imagen: File? = File.createTempFile(nombreImagen, ".JPEG", directiorio)
        if (imagen != null) {
            rutaImagen = imagen.absolutePath

        }
        Log.d("metodo create", imagen.toString())
        return imagen

    }

}