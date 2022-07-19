package com.example.infotrack.view

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.infotrack.data.utils.Constantes

class Permision(val contextActivity: DetailActivity) {

    fun getPermision(): Boolean {

        if (ContextCompat.checkSelfPermission(
                contextActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                contextActivity,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        )
        {
            ActivityCompat.requestPermissions(
                contextActivity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
                1000
            )
        }
        return true?: false


    }


}