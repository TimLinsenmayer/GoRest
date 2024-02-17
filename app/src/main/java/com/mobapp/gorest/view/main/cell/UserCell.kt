package com.mobapp.gorest.view.main.cell

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.mobapp.gorest.dto.UserDto


@Composable
fun UserCell (userDto: UserDto, userImage: ImageBitmap) {
    Card(
        modifier = Modifier.padding(5.dp).fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)){
        Row{
            Image(bitmap = userImage, contentDescription = "User Image")
            Column(modifier = Modifier.padding(5.dp)){
                Text(text ="${userDto.id}")
                Text(text = userDto.name)
            }
        }

    }
}