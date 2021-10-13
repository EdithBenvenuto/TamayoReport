package com.example.tamayoreport.model.repository.backendinterface

import com.example.tamayoreport.model.entities.JwtToken
import com.example.tamayoreport.model.entities.Report
import com.example.tamayoreport.model.entities.User
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ReportsApi {
    @GET("reports/{id}")
    fun getProduct(@Path("id") productId: String): Call<Report>

    @GET("reports/")
    fun getReports(): Call<List<Report>>

    @Multipart
    @POST("reports/")
    fun insertProduct(
        @Part report: MultipartBody.Part,
        @Part reportPhoto: MultipartBody.Part?
    ): Call<Report>


    /*@PUT("reports/")
    fun updateProduct(@Body id: String, estado : String): Call<Report>
     */
    @Multipart
    @PUT("reports/{id}")
    fun updateProduct(
        @Path("id") productId: String,
        @Part product: MultipartBody.Part,
        //@Part productPhoto: MultipartBody.Part?
    ): Call<Report>
   /* fun updateProduct(
        @Path("id") productId: String,
        @Part ("state") state: String
    ): Call<Report>
    */


    @GET("reports/{userId}")
    fun getUserReports(@Path("userId") userId: String): Call<List<Report>>
    //fun getUserReports(@Body userId: String): Call<List<Report>>

    @DELETE("reports/{id}")
    fun deleteProduct(@Path("reportId") productId: String): Call<Report>
}