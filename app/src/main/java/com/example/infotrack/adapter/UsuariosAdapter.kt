package com.example.infotrack.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.infotrack.data.model.Usuarios
import com.example.infotrack.databinding.UsuarioPortadaBinding
import kotlin.properties.Delegates

class UsuariosAdapter(): RecyclerView.Adapter<UsuariosVH>() {

    var usuarios: List<Usuarios> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = old[oldItemPosition]
                val newItem = new[newItemPosition]
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition] == new[newItemPosition]

        }).dispatchUpdatesTo(this)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosVH {
        val binding = UsuarioPortadaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return UsuariosVH(binding)
    }

    override fun onBindViewHolder(holder: UsuariosVH, position: Int) {
        val usuarios = usuarios[position]
        holder.bind(usuarios)
        holder.itemView.setOnClickListener { TODO("cargar el listener para instrucciones luego") }

    }

    override fun getItemCount() = this.usuarios.size


}