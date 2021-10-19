package com.example.tamayoreport.model.repository.responseinterface

import com.example.tamayoreport.model.entities.JwtToken

interface ILogin : IBasicResponse {
    fun onSuccess(token: JwtToken?)
}