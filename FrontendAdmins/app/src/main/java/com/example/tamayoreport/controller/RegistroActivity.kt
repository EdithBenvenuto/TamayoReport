package com.example.tamayoreport.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import com.example.tamayoreport.R
import com.example.tamayoreport.Utils
import com.example.tamayoreport.model.Model
import com.example.tamayoreport.model.entities.Admin
import com.example.tamayoreport.model.entities.User
import com.example.tamayoreport.model.repository.responseinterface.IAddUser

class RegistroActivity : AppCompatActivity() {
    lateinit var volver: Button
    lateinit var registro: Button
    lateinit var seleccion: RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        volver=findViewById<Button>(R.id.volver)
        registro=findViewById<Button>(R.id.confirmarRegistro)
        volver.setOnClickListener(volverClickListener())
        registro.setOnClickListener(registerClickListener())
        seleccion = findViewById(R.id.rgTypeAdmins)
    }
    private fun volverClickListener(): View.OnClickListener?{
        return View.OnClickListener{
            val switchActivityIntent = Intent(applicationContext, HomeScreenLoggedActivity::class.java)
            startActivity(switchActivityIntent);
        }
    }

    private fun registerClickListener(): View.OnClickListener?{
        return View.OnClickListener{

            var name = findViewById<EditText>(R.id.txtNombre).text.toString();
            val lastName = findViewById<EditText>(R.id.txtApellido).text.toString();
            val email = findViewById<EditText>(R.id.txtCorreoRegistro).text.toString();
            val password = findViewById<EditText>(R.id.txtContraRegistro).text.toString();
            val passwordConfirmation = findViewById<EditText>(R.id.txtConfContra).text.toString();
            name += " $lastName"
            var adminType: String = ""
            when (seleccion.checkedRadioButtonId) {
                R.id.rbiluminarias ->  adminType = "Gerente de conservación" //Toast.makeText(applicationContext, "Cambia estado a 'Recibido'", Toast.LENGTH_SHORT).show()
                R.id.rbdirector -> adminType = "Director" //Toast.makeText(applicationContext, "Cambia estado a 'En proceso'", Toast.LENGTH_SHORT).show()
                R.id.rbjardineria -> adminType = "Supervisor de jardinería"//Toast.makeText(applicationContext, "Cambia estado a 'Resuelto'", Toast.LENGTH_SHORT).show()
            }

            if (password == passwordConfirmation){
                if(password.length<7){
                    Toast.makeText(this, "La contraseña debe ser de mínimo 7 caracteres", Toast.LENGTH_LONG).show()
                }
                else {
                    val admin = Admin(
                        adminType,
                        true
                    )
                    val user = User(
                        "",
                        name,
                        email,
                        password,
                        admin,
                        true
                    )

                    Model(Utils.getToken(this)).addUsers(user, object : IAddUser {
                        override fun onSuccess(product: User?) {
                            Toast.makeText(
                                this@RegistroActivity,
                                "Datos enviados",
                                Toast.LENGTH_SHORT
                            ).show()
                            val switchActivityIntent =
                                Intent(applicationContext, LoginActivity::class.java)
                            startActivity(switchActivityIntent);
                        }

                        override fun onNoSuccess(code: Int, message: String) {
                            if (code == 500) {
                                Toast.makeText(
                                    this@RegistroActivity,
                                    "Correo electrónico inválido",
                                    Toast.LENGTH_LONG
                                ).show();
                            } else
                                Toast.makeText(
                                    this@RegistroActivity,
                                    "Problem detected $code $message",
                                    Toast.LENGTH_SHORT
                                ).show()
                        }

                        override fun onFailure(t: Throwable) {
                            Toast.makeText(
                                this@RegistroActivity,
                                "Network or server error occurred",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    )
                }
            }
            else{
                Toast.makeText(this@RegistroActivity, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }

}