package com.example.infotrack.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.infotrack.data.utils.Constantes
import com.example.infotrack.data.utils.Sumas
import com.example.infotrack.databinding.ActivityApruebaInfoBinding

class ApruebaInfoViewModel() : ViewModel() {

    var currentProgress = MutableLiveData<Int>()

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

}