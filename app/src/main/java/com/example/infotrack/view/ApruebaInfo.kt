package com.example.infotrack.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.infotrack.R
import com.example.infotrack.data.Camara
import com.example.infotrack.data.utils.Sumas
import com.example.infotrack.databinding.ActivityApruebaInfoBinding
import com.example.infotrack.viewModel.ApruebaInfoViewModel

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
                Toast.makeText(this, "Informacion enviada", Toast.LENGTH_SHORT)
                    .show()
                apruebaInfoViewModel.finaliza()
                apruebaInfoViewModel.currentProgress.observe(this,
                    Observer { currentProgress ->
                        binding.progresBar.setProgress(currentProgress)
                    })
                finish()
            }
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Camara.REQUEST_CODE && resultCode == RESULT_OK) {
            val imgBitmap: Bitmap? = BitmapFactory.decodeFile(Camara.rutaImagen)
            binding.photoTaken.setImageBitmap(imgBitmap)
            binding.photoTaken.setRotation(90F)
            Toast.makeText(this, "Informacion Lista Para Enviar", Toast.LENGTH_LONG).show()
            fotoReady = true
            apruebaInfoViewModel.checked()

        }

    }

}
