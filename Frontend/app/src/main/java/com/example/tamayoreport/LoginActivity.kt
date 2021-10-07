package com.example.tamayoreport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var iniciarSesion: Button
    lateinit var registro: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        iniciarSesion=findViewById<Button>(R.id.inicioSesion)
        registro=findViewById<Button>(R.id.registrate)
        iniciarSesion.setOnClickListener(loginClickListener())
        registro.setOnClickListener(registerClickListener())
    }
    private fun loginClickListener(): View.OnClickListener?{
        return View.OnClickListener{
            Toast.makeText(this, "Validar datos login", Toast.LENGTH_SHORT).show()
            val switchActivityIntent = Intent(applicationContext, HomeScreenLoggedActivity::class.java)
            startActivity(switchActivityIntent);
        }
    }

    private fun registerClickListener(): View.OnClickListener?{
        return View.OnClickListener{
            val switchActivityIntent = Intent(applicationContext, RegistroActivity::class.java);
            startActivity(switchActivityIntent);
        }
    }
}