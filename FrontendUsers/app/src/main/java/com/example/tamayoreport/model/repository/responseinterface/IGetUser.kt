package com.example.tamayoreport.model.repository.responseinterface

import com.example.tamayoreport.model.entities.User

interface IGetUser :IBasicResponse {
    fun onSuccess(product: User?)
}