package com.example.tamayoreport.model.repository.responseinterface

import com.example.tamayoreport.model.entities.User

interface IGetUsers: IBasicResponse {
    fun onSuccess(products: List<User>?)
}