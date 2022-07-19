package com.example.infotrack.data.db.entities

import androidx.room.*

@Dao
interface UsuariosDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(usuarios: Usuarios)


    @Delete
    fun deleteUser(usuarios: Usuarios)


    @Query("SELECT * from UsuariosEntity ORDER BY Id ASC")
    suspend fun getUsuariosFromDB() : List<Usuarios>

}