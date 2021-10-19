package com.example.tamayoreport.model

import com.example.tamayoreport.model.entities.JwtToken
import com.example.tamayoreport.model.entities.Report
import com.example.tamayoreport.model.entities.User
import com.example.tamayoreport.model.repository.RemoteRepository
import com.example.tamayoreport.model.repository.backendinterface.ReportsApi
import com.example.tamayoreport.model.repository.backendinterface.UsersApi
import com.example.tamayoreport.model.repository.responseinterface.*
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Model (private val token:String){
    fun getReports(callback: IGetReports) {
        val retrofit = RemoteRepository.getRetrofitInstance(token)
        val callGetUser = retrofit.create(ReportsApi::class.java).getReports()
        callGetUser.enqueue(object : Callback<List<Report>?> {
            override fun onResponse(
                call: Call<List<Report>?>,
                response: Response<List<Report>?>
            ) {
                if (response.isSuccessful) callback.onSuccess(response.body())
                else callback.onNoSuccess(response.code(), response.message())
            }

            override fun onFailure(call: Call<List<Report>?>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    fun addReport(product: Report, productPhotoBytes: ByteArray, callback: IAddReport) {
        val bodyProductPhoto =
            RequestBody.create(MediaType.parse("application/octet-stream"), productPhotoBytes)
        val partProductPhoto =
            MultipartBody.Part.createFormData("photo", "product.png", bodyProductPhoto)

        val productAsJson = Gson().toJson(product)
        val productPart = MultipartBody.Part.createFormData("product", productAsJson)

        val retrofit = RemoteRepository.getRetrofitInstance(token)
        // val callAddProduct = retrofit.create(ProductsApi::class.java).insertProduct(productPart, partProductPhoto)
        val callAddProduct: Call<Report> = if (productPhotoBytes.isEmpty())
            retrofit.create(ReportsApi::class.java).insertProduct(productPart, null)
        else
            retrofit.create(ReportsApi::class.java)
                .insertProduct(productPart, partProductPhoto)

        callAddProduct.enqueue(object : Callback<Report?> {
            override fun onResponse(call: Call<Report?>, response: Response<Report?>) {
                if (response.isSuccessful) {
                    callback.onSuccess(product)
                } else {
                    // callback.onNoSuccess(response.code(), "something went wrong")
                    val message: String = if (response.errorBody() != null)
                        response.errorBody()!!.string()
                    else
                        response.message()
                    callback.onNoSuccess(response.code(), message)
                }
            }

            override fun onFailure(call: Call<Report?>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
    fun getReport(productId: String, callback: IGetReport) {
        val retrofit = RemoteRepository.getRetrofitInstance(token)
        val callGetProduct = retrofit.create(ReportsApi::class.java).getProduct(productId)

        callGetProduct.enqueue(object : Callback<Report?> {
            override fun onResponse(call: Call<Report?>, response: Response<Report?>) {
                if (response.isSuccessful)
                    callback.onSuccess(response.body())
                else {
                    val message: String = if (response.errorBody() != null)
                        response.errorBody()!!.string()
                    else
                        response.message()
                    callback.onNoSuccess(response.code(), message)
                }
            }

            override fun onFailure(call: Call<Report?>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
    fun getUserReports(userId: String, callback: IGetReports) {
        val retrofit = RemoteRepository.getRetrofitInstance(token)
        val callGetUser = retrofit.create(ReportsApi::class.java).getUserReports(userId)
        callGetUser.enqueue(object : Callback<List<Report>?> {
            override fun onResponse(
                call: Call<List<Report>?>,
                response: Response<List<Report>?>
            ) {
                if (response.isSuccessful) callback.onSuccess(response.body())
                else callback.onNoSuccess(response.code(), response.message())
            }

            override fun onFailure(call: Call<List<Report>?>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
    fun updateReport(report: Report, callback: IUpdateReport) {
        val productAsJson = Gson().toJson(report)
        val productPart = MultipartBody.Part.createFormData("product", productAsJson)

        val retrofit = RemoteRepository.getRetrofitInstance(token)

        val callUpdateProduct = retrofit.create(ReportsApi::class.java).updateProduct(report.id,productPart)

        callUpdateProduct.enqueue(object : Callback<Report?> {
            override fun onResponse(call: Call<Report?>, response: Response<Report?>) {
                if (response.isSuccessful) callback.onSuccess(response.body())
                else {
                    val message: String = if (response.errorBody() != null)
                        response.errorBody()!!.string()
                    else
                        response.message()
                    callback.onNoSuccess(response.code(), message)
                }
            }

            override fun onFailure(call: Call<Report?>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    fun deleteProduct(productId: String, callback: IDeleteProduct) {
        val retrofit = RemoteRepository.getRetrofitInstance(token)

        val callDeleteProduct = retrofit.create(ReportsApi::class.java).deleteProduct(productId)

        callDeleteProduct.enqueue(object : Callback<Report?> {
            override fun onResponse(call: Call<Report?>, response: Response<Report?>) {
                if (response.isSuccessful)
                    callback.onSuccess(response.body())
                else {
                    val message: String = if (response.errorBody() != null)
                        response.errorBody()!!.string()
                    else
                        response.message()
                    callback.onNoSuccess(response.code(), message)
                }
            }

            override fun onFailure(call: Call<Report?>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
    fun login(user: User, callback: ILogin){
        val retrofit = RemoteRepository.getRetrofitInstance(token)

        val callLogin = retrofit.create(UsersApi::class.java).login(user)

        callLogin.enqueue(object : Callback<JwtToken?> {
            override fun onResponse(call: Call<JwtToken?>, response: Response<JwtToken?>) {
                if (response.isSuccessful) callback.onSuccess(response.body())
                else {
                    val message: String = if (response.errorBody() != null)
                        response.errorBody()!!.string()
                    else
                        response.message()
                    callback.onNoSuccess(response.code(), message)
                }
            }//

            override fun onFailure(call: Call<JwtToken?>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
    fun getUsers(callback: IGetUsers) {
        val retrofit = RemoteRepository.getRetrofitInstance(token)
        val callGetUsers = retrofit.create(UsersApi::class.java).getUsers()
        callGetUsers.enqueue(object : Callback<List<User>?> {
            override fun onResponse(
                call: Call<List<User>?>,
                response: Response<List<User>?>
            ) {
                if (response.isSuccessful) callback.onSuccess(response.body())
                else callback.onNoSuccess(response.code(), response.message())
            }

            override fun onFailure(call: Call<List<User>?>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
    fun addUsers(product: User, callback: IAddUser) {

        val retrofit = RemoteRepository.getRetrofitInstance(token)
        val callAddUser = retrofit.create(UsersApi::class.java).addUsers(product)

        callAddUser.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                if (response.isSuccessful) {
                    callback.onSuccess(product)
                } else {
                    // callback.onNoSuccess(response.code(), "something went wrong")
                    val message: String = if (response.errorBody() != null)
                        response.errorBody()!!.string()
                    else
                        response.message()
                    callback.onNoSuccess(response.code(), message)
                }
            }

            override fun onFailure(call: Call<User?>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
    fun getUser(userId: String, callback: IGetUser) {
        val retrofit = RemoteRepository.getRetrofitInstance(token)
        val callGetUser = retrofit.create(UsersApi::class.java).getUser(userId)

        callGetUser.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                if (response.isSuccessful)
                    callback.onSuccess(response.body())
                else {
                    val message: String = if (response.errorBody() != null)
                        response.errorBody()!!.string()
                    else
                        response.message()
                    callback.onNoSuccess(response.code(), message)
                }
            }

            override fun onFailure(call: Call<User?>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
    fun deleteUser(UserId: String, callback: IDeleteUser) {
        val retrofit = RemoteRepository.getRetrofitInstance(token)

        val callDeleteUser = retrofit.create(UsersApi::class.java).deleteProduct(UserId)

        callDeleteUser.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                if (response.isSuccessful)
                    callback.onSuccess(response.body())
                else {
                    val message: String = if (response.errorBody() != null)
                        response.errorBody()!!.string()
                    else
                        response.message()
                    callback.onNoSuccess(response.code(), message)
                }
            }
            override fun onFailure(call: Call<User?>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}