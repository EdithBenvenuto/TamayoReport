package com.example.tamayoreport.model.entities

import com.google.gson.annotations.SerializedName
import java.util.*

class Report (
    @SerializedName("_id")
    var id: String,
    var category: String,
    var photo: String?,
    var date: String,
    var ubication: String,
    var description: String,
    var state:String
){
}