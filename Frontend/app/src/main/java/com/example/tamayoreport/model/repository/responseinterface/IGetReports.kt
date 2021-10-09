package com.example.tamayoreport.model.repository.responseinterface

import com.example.tamayoreport.model.entities.Report

interface IGetReports : IBasicResponse {
    fun onSuccess(products: List<Report>?)
}