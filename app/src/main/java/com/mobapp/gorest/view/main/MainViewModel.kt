package com.mobapp.gorest.view.main

import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobapp.gorest.dto.UserDto
import com.mobapp.gorest.service.UserService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val userService = UserService()
    var users: MutableList<UserDto> by mutableStateOf(mutableListOf())
    lateinit var userImage: ImageBitmap
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)
    fun getAllUsers() {
        viewModelScope.launch {
            errorMessage = ""
            loading = true
            try {
                val allUsers = userService.getAllUsers()
                val loadedUserImage = userService.getUserImage()
                val bitmap = BitmapFactory.decodeByteArray(loadedUserImage, 0,
                    loadedUserImage.size)
                userImage = bitmap.asImageBitmap()
                loading = false
                users = allUsers.toMutableList()
            } catch (e: Exception) {
                loading = false
                errorMessage = e.message.toString()
            }
        }
    }
}