package com.mobapp.gorest.view.userDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.mobapp.gorest.dto.UserDto
import com.mobapp.gorest.service.UserService
import kotlinx.coroutines.launch

class UserDetailViewModel : ViewModel() {
    lateinit var navController: NavController
    private val userService = UserService()
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)

    fun updateUser(userDto: UserDto) {
        viewModelScope.launch {
            loading = true
            try {
                userService.updateUser(userDto)
                loading = false
                navController.navigateUp()
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
            }
        }
    }
    fun deleteUser(userDto: UserDto) {
        viewModelScope.launch {
            loading = true
            try {
                userService.deleteUser(userDto.id)
                loading = false
                navController.navigateUp()
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
            }
        }
    }
    fun addUser(userDto: UserDto) {
        viewModelScope.launch{
            loading = true
            try{
                userService.addUser(userDto)
                loading = false
                navController.navigateUp()
            } catch(e: Exception) {
                loading = false
                errorMessage = e.message
                    .toString()
            }
        }
    }

}