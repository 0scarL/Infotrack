package com.example.infotrack.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.infotrack.data.model.Usuarios
import com.example.infotrack.databinding.UsuarioPortadaBinding

class UsuariosVH(private val binding: UsuarioPortadaBinding): RecyclerView.ViewHolder(binding.root) {

    //private val binding: UsuarioItemLayoutBinding

    fun bind(usuarios: Usuarios) {
        binding.tvName.text = usuarios.name
        binding.tvEmail.text = usuarios.email
        Log.d("adapter", usuarios.email)

    }
}