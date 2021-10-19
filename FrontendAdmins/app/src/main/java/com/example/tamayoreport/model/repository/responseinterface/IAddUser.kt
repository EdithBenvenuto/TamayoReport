package com.example.tamayoreport.model.repository.responseinterface

import com.example.tamayoreport.model.entities.User

interface IAddUser: IBasicResponse {
    fun onSuccess(product: User?)
}