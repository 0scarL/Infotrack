package com.example.infotrack.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.infotrack.adapter.UsuariosAdapter
import com.example.infotrack.data.model.Usuarios
import com.example.infotrack.data.net.UsuariosService
import com.example.infotrack.data.utils.UsuarioCarrier
import com.example.infotrack.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var usuariosService = UsuariosService()

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("Main Activity", "probando")


        var usuariosAdapter = UsuariosAdapter { usuarios -> navegateTo(usuarios) }
        binding.recyclerView.adapter = usuariosAdapter
        traerUsuarios(usuariosAdapter)

    }


    private fun navegateTo(usuarios: Usuarios) {
        val intent = Intent(this, DetailActivity::class.java)
        UsuarioCarrier.unUsuario = usuarios
        startActivity(intent)

    }



    private fun traerUsuarios(usuariosAdapter: UsuariosAdapter) {
        lifecycleScope.launch {
            val user = usuariosService.getUsuarios()
            usuariosAdapter.usuarios = user
            Log.d("Hilo Secundario", user.toString())


        }

    }

}