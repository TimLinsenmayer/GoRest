package com.mobapp.gorest.view.userDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mobapp.gorest.dto.UserDto
import io.ktor.client.utils.EmptyContent.status

class UserViewDto(
    var id: Int, name: String, email: String, gender: String, status: String ) {
    companion object
    { fun from(userDto: UserDto): UserViewDto
    { return UserViewDto( userDto.id, userDto.name, userDto.email, userDto.gender, userDto.status )
    }
    }
    var name: String by mutableStateOf(name)
    var email: String by mutableStateOf(email)
    var gender: String by mutableStateOf(gender)
    var status: String by mutableStateOf(status)
    fun toUserDto(): UserDto
    { return UserDto(id, name, email, gender, status
    )
    }
}