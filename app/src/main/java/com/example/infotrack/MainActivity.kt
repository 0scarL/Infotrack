package com.example.infotrack

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.infotrack.adapter.UsuariosAdapter
import com.example.infotrack.data.net.UsuariosService
import com.example.infotrack.data.utils.Variables
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




        var usuariosAdapter = UsuariosAdapter()
        binding.recyclerView.adapter = usuariosAdapter
        traerUsuarios(usuariosAdapter)

        Log.d("Main activity", Variables.users.toString())

    }
    private fun traerUsuarios(usuariosAdapter: UsuariosAdapter) {
        lifecycleScope.launch{
            val user = usuariosService.getUsuarios()
            usuariosAdapter.usuarios = user
            Log.d("Hilo Secundario", user.toString())


        }

    }

}