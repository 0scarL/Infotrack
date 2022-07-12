package com.example.infotrack.data.model

data class UsuariosItem(
    val id: Int,
    val address: Address,
    val company: Company,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)