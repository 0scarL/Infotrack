package com.example.infotrack.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.infotrack.databinding.ActivityApruebaInfoBinding

class ApruebaInfo : AppCompatActivity() {
    private lateinit var bindin: ActivityApruebaInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        bindin = ActivityApruebaInfoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindin.root)
    }
}