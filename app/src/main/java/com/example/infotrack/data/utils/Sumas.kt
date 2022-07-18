package com.example.infotrack.data.utils

object Sumas {
    private var currentProgress: Int = 0

    fun setSumando() {
        currentProgress = currentProgress + 50
    }

    fun setRestando() {
        currentProgress = currentProgress - 50
    }


    fun     getTermino() = currentProgress

    fun clear(){
        currentProgress = 0
    }


}