package com.example.tamayoreport.model.entities

import com.google.gson.annotations.SerializedName
import java.util.*

class Report (
    @SerializedName("_id")
    var id: String,
    var categoria: String,
    var foto: String?,
    var fechaReporte: String,
    var ubicacion: String,
    var descripcion: String,
    var estado:String
){
}