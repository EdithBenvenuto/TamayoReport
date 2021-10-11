package com.example.tamayoreport.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.tamayoreport.R

class RegistroActivity : AppCompatActivity() {
    lateinit var iniciarSesion: Button
    lateinit var registro: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        iniciarSesion=findViewById<Button>(R.id.inicioSesion)
        registro=findViewById<Button>(R.id.confirmarRegistro)
        iniciarSesion.setOnClickListener(loginClickListener())
        registro.setOnClickListener(registerClickListener())
    }
    private fun loginClickListener(): View.OnClickListener?{
        return View.OnClickListener{
            val switchActivityIntent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(switchActivityIntent);
        }
    }

    private fun registerClickListener(): View.OnClickListener?{

        return View.OnClickListener{
            Toast.makeText(this, "Validar datos registro", Toast.LENGTH_SHORT).show()
            val switchActivityIntent = Intent(applicationContext, HomeScreenLoggedActivity::class.java)
            startActivity(switchActivityIntent);
        }
    }

}