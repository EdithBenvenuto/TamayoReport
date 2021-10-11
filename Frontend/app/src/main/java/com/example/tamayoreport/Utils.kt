package com.example.tamayoreport

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tamayoreport.model.entities.JwtToken

class Utils {
    companion object {
        val BASE_URL = "http://127.0.0.1:3000/"
        val PRODUCT_ID_KEY = "productId"

        private const val TOKEN_PREFS = "tokenPrefs"
        private const val TOKEN_KEY = "tokenKey"
        private const val FIREBASE_TOKEN_KEY = "firebaseTokenKey"

        fun getToken(context: Context): String {
            val sharedPreferences = context.getSharedPreferences(
                TOKEN_PREFS,
                AppCompatActivity.MODE_PRIVATE
            )
            val token = sharedPreferences.getString(TOKEN_KEY, "WRONG_TOKEN")
            Log.i("Utils", "Token is ${token!!}")
            return token
        }

        fun saveToken(token: JwtToken, context: Context) {
            val sharedPreferences = context.getSharedPreferences(
                TOKEN_PREFS,
                AppCompatActivity.MODE_PRIVATE
            )
            val editor = sharedPreferences.edit()
            editor.putString(TOKEN_KEY, token.token)
            editor.commit()
        }

        fun isUserLoggedIn(context: Context): Boolean {
            val sharedPreferences = context.getSharedPreferences(
                TOKEN_PREFS,
                AppCompatActivity.MODE_PRIVATE
            )
            return sharedPreferences.contains(TOKEN_KEY)
        }

        fun saveFirebaseToken(token:String, context: Context){
            val sharedPreferences = context.getSharedPreferences(
                TOKEN_PREFS,
                AppCompatActivity.MODE_PRIVATE
            )
            val editor = sharedPreferences.edit()
            editor.putString(FIREBASE_TOKEN_KEY, token)
            editor.commit()
        }
    }
}