package com.mobapp.gorest.service

import com.mobapp.gorest.api.APIManager
import com.mobapp.gorest.dto.UserDto
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsChannel
import io.ktor.http.contentLength
import io.ktor.utils.io.ByteReadChannel

class UserService {
    private val apiManager = APIManager()
    suspend fun getAllUsers(): List<UserDto> {
        return apiManager.jsonHttpClient.get("users").body()
    }
    suspend fun addUser(userDto: UserDto): UserDto {
        return apiManager.jsonHttpClient.post("users") {
            setBody(
                userDto
            )
        }.body()
    }

    suspend fun getUser(id: Int): UserDto {
        return apiManager.jsonHttpClient.get("users/$id").body()
    }
    suspend fun updateUser(userDto: UserDto){
        return apiManager.jsonHttpClient.put("users/${userDto.id}"){
            setBody(
                userDto
            )
        }.body()
    }
    suspend fun deleteUser(id: Int){
        return apiManager.jsonHttpClient.delete("users/$id").body()
    }
    suspend fun getUserImage(): ByteArray {
        val httpResponse: HttpResponse =
            apiManager.imageHttpClient.get("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/Bundesarchiv_Bild_183-J1231-1002-002_Walter_Ulbricht%2C_Neujahrsansprache.jpg/220px- Bundesarchiv_Bild_183-J1231-1002-002_Walter_Ulbricht%2C_Neujahrsansprache.jpg")
                val bytes: ByteReadChannel = httpResponse.bodyAsChannel()
        val byteBufferSize = 1024 * 100
        val byteBuffer = ByteArray(httpResponse.contentLength()!!.toInt())
        var read = 0
        do {
            val currentRead = bytes.readAvailable(byteBuffer, read, byteBufferSize)
            if (currentRead > 0) {
                read += currentRead
            } } while (currentRead >= 0)
        return byteBuffer
    }

}