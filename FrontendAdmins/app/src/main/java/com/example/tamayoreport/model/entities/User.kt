package com.example.tamayoreport.model.entities

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class User(
    var name: String?,


    var email: String?,


    var password: String?,


    var admin: Admin?,


    var online: Boolean?,

    @SerializedName("_id")
    @Expose
    var id: String? = null) {



}
