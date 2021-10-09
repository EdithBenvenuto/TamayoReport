package com.example.tamayoreport.model.entities

import com.google.gson.annotations.SerializedName

class Report (
    @SerializedName("_id") var id: String,
    var description: String,
    var ubication: String,
    var photo: String?
){
}