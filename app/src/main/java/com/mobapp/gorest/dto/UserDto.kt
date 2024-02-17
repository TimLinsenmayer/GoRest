package com.mobapp.gorest.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: Int,
    var name: String,
    var email: String,
    var gender: String,
    var status: String
)