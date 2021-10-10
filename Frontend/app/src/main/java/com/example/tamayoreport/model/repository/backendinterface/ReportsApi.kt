package com.example.tamayoreport.model.repository.backendinterface

import com.example.tamayoreport.model.entities.Report
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ReportsApi {
    @GET("reports/{id}")
    fun getProduct(@Path("id") productId: String): Call<Report>

    @GET("reports/")
    fun getProducts(): Call<List<Report>>

    @Multipart
    @POST("reports/")
    fun insertProduct(
        @Part report: MultipartBody.Part,
        @Part reportPhoto: MultipartBody.Part?
    ): Call<Report>

    @Multipart
    @PUT("reports/{id}")
    fun updateProduct(
        @Path("id") productId: String,
        @Part product: MultipartBody.Part,
        @Part productPhoto: MultipartBody.Part?
    ): Call<Report>

    @DELETE("reports/{id}")
    fun deleteProduct(@Path("reportId") productId: String): Call<Report>
}