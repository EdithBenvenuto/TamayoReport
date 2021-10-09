package com.example.tamayoreport.model.repository.responseinterface

import com.example.tamayoreport.model.entities.Report

interface IGetReport :IBasicResponse {
    fun onSuccess(product: Report?)
}