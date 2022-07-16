package com.example.infotrack.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.infotrack.adapter.UsuariosAdapter
import com.example.infotrack.data.model.Usuarios
import com.example.infotrack.data.net.UsuariosService
import com.example.infotrack.data.utils.Constantes
import com.example.infotrack.data.utils.UsuarioCarrier
import com.example.infotrack.databinding.ActivityMainBinding
import com.example.infotrack.viewModel.UsuariosViewModel

class MainActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val productViewModel: UsuariosViewModel by viewModels()

    var usuariosService = UsuariosService()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        productViewModel.onCreate()



       productViewModel.loading.observe(this, Observer {
            binding.myProgressbar.isVisible = it
        })

        var usuariosAdapter = UsuariosAdapter { usuarios -> navegateTo(usuarios) }
        binding.recyclerView.adapter = usuariosAdapter

        productViewModel.searchResult.observe(this, Observer { searchGood ->
            if (!searchGood)
                binding.tvSearchError.text = Constantes.NO_RESULTADO
        })

        productViewModel.usuarios.observe(this, Observer{ listaUsuarios ->
            if(!listaUsuarios.isNullOrEmpty()){
            usuariosAdapter.usuarios = listaUsuarios
            }
        })


   }


    private fun navegateTo(usuarios: Usuarios) {
        val intent = Intent(this, DetailActivity::class.java)
        UsuarioCarrier.unUsuario = usuarios
        startActivity(intent)

    }



//    private fun traerUsuarios(usuariosAdapter: UsuariosAdapter) {
//        lifecycleScope.launch {
//            val user = usuariosService.getUsuarios()
//            usuariosAdapter.usuarios = user
//            Log.d("Hilo Secundario", user.toString())
//
//
//        }
//
//    }

}