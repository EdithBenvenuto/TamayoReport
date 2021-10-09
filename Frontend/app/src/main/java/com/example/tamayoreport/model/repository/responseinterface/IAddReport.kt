package com.example.tamayoreport.model.repository.responseinterface

import com.example.tamayoreport.model.entities.Report

interface IAddReport : IBasicResponse {
    fun onSuccess(product: Report?)
}