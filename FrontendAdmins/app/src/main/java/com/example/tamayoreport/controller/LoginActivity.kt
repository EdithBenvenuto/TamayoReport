package com.example.tamayoreport.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.example.tamayoreport.model.entities.JwtToken
import com.example.tamayoreport.model.entities.User
import com.example.tamayoreport.model.repository.RemoteRepository
import com.example.tamayoreport.model.repository.responseinterface.ILogin

class LoginActivity : AppCompatActivity() {
    lateinit var iniciarSesion: Button
   lateinit var  sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sharedPreferences = this.getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE)
        iniciarSesion=findViewById<Button>(R.id.volver)

        iniciarSesion.setOnClickListener(loginClickListener())

    }
    private fun loginClickListener(): View.OnClickListener?{
        return View.OnClickListener{
            /*val switchActivityIntent = Intent(applicationContext, HomeScreenLoggedActivity::class.java)
            startActivity(switchActivityIntent);*/
            val email = findViewById<EditText>(R.id.txtCorreo).text.toString()
            val password = findViewById<EditText>(R.id.txtContra).text.toString()

            val user = User("","anyname", email, password, "",false, true)

            Model(Utils.getToken(this)).login(user, object : ILogin {
                override fun onSuccess(token: JwtToken?) {
                    if (token != null) {
                        Utils.saveToken(token, this@LoginActivity.applicationContext)
                        // This updates the HttpClient that at this moment might not have a valid token!
                        RemoteRepository.updateRemoteReferences(token.token, this@LoginActivity);
                        // TODO: SEND THE FIREBASE TOKEN (fcmToken, userId)
                        val editor = sharedPreferences.edit()
                        editor.putString("shareIdUser", token.userId)
                        editor.putBoolean("admin",token.admin)
                        if(token.admin){
                            Toast.makeText(this@LoginActivity , "Accediste como admin", Toast.LENGTH_SHORT).show()
                            editor.apply()
                            advanceToMainActivity()
                        }
                        else{
                            Toast.makeText(this@LoginActivity , "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                        }


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
    private fun advanceToMainActivity() {
        val mainActivityIntent =
            Intent(applicationContext, HomeScreenLoggedActivity::class.java)
        mainActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(mainActivityIntent)
    }


}