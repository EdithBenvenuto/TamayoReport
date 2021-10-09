package com.example.tamayoreport.model.repository.backendinterface

import com.example.tamayoreport.model.entities.JwtToken
import com.example.tamayoreport.model.entities.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersApi {
    @POST("users/login")
    fun login(@Body user: User): Call<JwtToken>
}