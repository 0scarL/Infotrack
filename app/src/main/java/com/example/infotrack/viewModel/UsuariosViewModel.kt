package com.example.infotrack.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infotrack.data.model.Usuarios
import com.example.infotrack.data.utils.UsuarioCarrier
import com.example.infotrack.domain.GetUsuariosUseCase
import kotlinx.coroutines.launch

class UsuariosViewModel : ViewModel() {

    private var getUsuariosUseCase = GetUsuariosUseCase()
    val usuarios =MutableLiveData<List<Usuarios>>()
    val searchResult = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun onCreate() {

        onProgressBar()
        viewModelScope.launch {

            val result = getUsuariosUseCase()
            if (!result.isNullOrEmpty()) {
                usuarios.postValue(result)
                UsuarioCarrier.usuarios = result
                searchResult.postValue(true)
            }else {
                searchResult.postValue(false)
            }
            offProgresBar()
        }

    }

    private fun onProgressBar() = loading.postValue(true)

    private fun offProgresBar() = loading.postValue(false)


}