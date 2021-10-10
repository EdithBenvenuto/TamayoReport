package com.example.tamayoreport.model.repository.responseinterface

import com.example.tamayoreport.model.entities.User

interface IDeleteUser :IBasicResponse {
    fun onSuccess(product: User?)
}