package com.example.tamayoreport.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tamayoreport.R
import com.example.tamayoreport.Utils
import com.example.tamayoreport.model.Model
import com.example.tamayoreport.model.entities.Admin
import com.example.tamayoreport.model.entities.JwtToken
import com.example.tamayoreport.model.entities.User
import com.example.tamayoreport.model.repository.RemoteRepository
import com.example.tamayoreport.model.repository.responseinterface.ILogin

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
            /*val switchActivityIntent = Intent(applicationContext, HomeScreenLoggedActivity::class.java)
            startActivity(switchActivityIntent);*/
            val email = findViewById<EditText>(R.id.txtCorreo).text.toString()
            val password = findViewById<EditText>(R.id.txtContra).text.toString()
            val admin = Admin("anytype", false)
            val user = User("","anyname", email, password, admin, true)

            Model(Utils.getToken(this)).login(user, object : ILogin {
                override fun onSuccess(token: JwtToken?) {
                    if (token != null) {
                        Utils.saveToken(token, this@LoginActivity.applicationContext)
                        // This updates the HttpClient that at this moment might not have a valid token!
                        RemoteRepository.updateRemoteReferences(token.token, this@LoginActivity);
                        // TODO: SEND THE FIREBASE TOKEN (fcmToken, userId)

                        advanceToMainActivity(token.userId)
                    } else {
                        // do not advance, an error occurred
                        Toast.makeText(
                            this@LoginActivity,
                            "Something weird happened, login was ok but token was not given...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                override fun onNoSuccess(code: Int, message: String) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Problem detected $code $message",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("addReport", "$code: $message")
                }

                override fun onFailure(t: Throwable) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Network or server error occurred",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("addReport", t.message.toString())
                }
            })
        }
    }
    private fun advanceToMainActivity(userId: String) {
        val userid = Bundle();
        userid.putString("userId",userId)
        val mainActivityIntent =
            Intent(applicationContext, HomeScreenLoggedActivity::class.java)
        mainActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        mainActivityIntent.putExtras(userid)
        startActivity(mainActivityIntent)
    }

    private fun registerClickListener(): View.OnClickListener?{
        return View.OnClickListener{
            val switchActivityIntent = Intent(applicationContext, RegistroActivity::class.java);
            startActivity(switchActivityIntent);
        }
    }
}