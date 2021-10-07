package com.example.tamayoreport

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var iniciarSesion:Button
    lateinit var registro:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarSesion=findViewById<Button>(R.id.inicioSesion)
        registro=findViewById<Button>(R.id.registrate)
        iniciarSesion.setOnClickListener(loginClickListener())
        registro.setOnClickListener(registerClickListener())
    }

    private fun loginClickListener():View.OnClickListener?{
        return View.OnClickListener{
            val switchActivityIntent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(switchActivityIntent);
        }
    }

    private fun registerClickListener():View.OnClickListener?{
        return View.OnClickListener{
            val switchActivityIntent = Intent(applicationContext, RegistroActivity::class.java);
            startActivity(switchActivityIntent);
        }
    }

}