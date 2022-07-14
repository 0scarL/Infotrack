package com.example.infotrack.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.infotrack.data.model.Usuarios
import com.example.infotrack.databinding.UsuarioPortadaBinding

class UsuariosVH(private val binding: UsuarioPortadaBinding): RecyclerView.ViewHolder(binding.root) {

    //private val binding: UsuarioItemLayoutBinding

    fun bind(usuario: Usuarios) {
        binding.tvName.text = usuario.name
        binding.tvEmail.text = usuario.email
        Log.d("adapter", usuario.email)

    }
}