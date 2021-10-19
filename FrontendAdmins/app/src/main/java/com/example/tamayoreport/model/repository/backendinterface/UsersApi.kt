package com.example.tamayoreport.model.repository.backendinterface

import com.example.tamayoreport.model.entities.JwtToken
import com.example.tamayoreport.model.entities.Report
import com.example.tamayoreport.model.entities.User
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface UsersApi {
    @PUT("users/login")
    fun login(@Body user: User): Call<JwtToken>

    @POST("users/")
    fun addUsers(
        @Body user:User
    ): Call<User>

    @GET("users/{idUsuario}")
    fun getUser(@Path("idUsuario") userId: String): Call<User>

    @GET("users/")
    fun getUsers(): Call<List<User>>

    @DELETE("users/{idUsuario}")
    fun deleteProduct(@Path("idUsuario") userId: String): Call<User>

}