package com.example.infotrack

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.infotrack.data.net.UsuariosService
import com.example.infotrack.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var usuariosService = UsuariosService()

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("Main Activity","probando")


        traerUsuarios()


    }
    private fun traerUsuarios() {
        lifecycleScope.launch{
            val user = usuariosService.getUsuarios()
            Log.d("Hilo Secundario", user.toString())

        }

    }

}