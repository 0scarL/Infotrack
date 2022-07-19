package com.example.infotrack.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infotrack.data.db.entities.Usuarios
import com.example.infotrack.data.utils.Sumas
import com.example.infotrack.data.utils.UsuarioCarrier
import com.example.infotrack.domain.GetUsuariosFromDbCase
import com.example.infotrack.domain.InsertUsuarioDBCase
import kotlinx.coroutines.launch

class ApruebaInfoViewModel() : ViewModel() {

    var currentProgress = MutableLiveData<Int>()
    private var insertUsuarioDBCase = InsertUsuarioDBCase()
    private var getUsuariosFromDbCase = GetUsuariosFromDbCase()
    var userFromDB = MutableLiveData<List<Usuarios>>()


    fun checked() {
        Sumas.setSumando()
        currentProgress.postValue(Sumas.getTermino())

    }

    fun unChek() {
        Sumas.setRestando()
        currentProgress.postValue(Sumas.getTermino())
    }

    fun finaliza() {
        Sumas.clear()
        currentProgress.postValue(Sumas.getTermino())
    }

    fun insertUsuario() {

        viewModelScope.launch {
            insertUsuarioDBCase(UsuarioCarrier.user)
            Log.d("insertando usuario id", UsuarioCarrier.user.Id.toString())


        }
    }

    fun getUsuariosDB() {
        viewModelScope.launch {
            val dbResult = getUsuariosFromDbCase()
            if (!dbResult.isNullOrEmpty()) {
                userFromDB.postValue(dbResult)

            }
        }
    }
}


