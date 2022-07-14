package com.example.infotrack.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.example.infotrack.data.utils.UsuarioCarrier
import com.example.infotrack.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("Detail Activity", "llega dato" + UsuarioCarrier.unUsuario)
        Toast.makeText(this, UsuarioCarrier.unUsuario.name, Toast.LENGTH_SHORT).show()

        UsuarioCarrier.unUsuario.let { usuario ->

            binding.tvDetailInfo.text = buildSpannedString {

                bold { append("Nombre de Usuario: ") }
                if (usuario.name != null) appendLine(usuario.name)

                bold { append("User Name: ") }
                if (usuario.username != null)
                    appendLine(usuario.username)

                bold { append("Ciudad : ") }
                if (usuario.address.city != null) appendLine(usuario.address.city)
            }

            binding.tvDetailCompany.text = buildSpannedString {

                bold { append("Nombre de Compañía: ") }
                if (usuario.company.name != null) appendLine(usuario.company.name)

                bold { append("BS: ") }
                if (usuario.company.bs != null)
                    appendLine(usuario.company.bs)

                bold { append("Eslogan o Catchphrase: ") }
                if (usuario.address.city != null) appendLine(usuario.address.city)
            }
        }

    }

}
