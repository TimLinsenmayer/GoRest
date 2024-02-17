package com.mobapp.gorest.view.userDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobapp.gorest.R
import com.mobapp.gorest.dto.UserDto
import com.mobapp.gorest.ui.theme.GoRestTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailView(userDto: UserDto, viewModel: UserDetailViewModel){
    val userViewDto by remember { mutableStateOf(UserViewDto.from(userDto)) }
    Scaffold(topBar = {TopAppBar(title = {Text(stringResource(R.string.editUser))})}) { padding ->
        Column (modifier = Modifier
            .padding(padding)
            .padding(horizontal = 5.dp)
            .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            if (viewModel.loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            if (viewModel.errorMessage != ""){
                Text(text = viewModel.errorMessage)
            }
            UserDetailViewContent(userViewDto, viewModel)
        }
    }
}

@Composable
fun UserDetailViewContent(userViewDto: UserViewDto, viewModel: UserDetailViewModel){
    fun updateCanSave(): Boolean {
        return userViewDto.name.isNotEmpty() &&
                userViewDto.email.isNotEmpty() &&
                userViewDto.gender.isNotEmpty()
    }
    var canSave: Boolean by remember {mutableStateOf(updateCanSave())}
    if (userViewDto.id != -1) {
        Text(text = "ID: ${userViewDto.id}", modifier = Modifier.fillMaxWidth())
    }
    TextField(value = userViewDto.name, onValueChange = {userViewDto.name = it;canSave=updateCanSave()},modifier = Modifier.fillMaxWidth(), isError = (userViewDto.name.isEmpty()), placeholder = { Text("Namen eingeben") } )
    TextField(value = userViewDto.email, onValueChange = {userViewDto.email = it;canSave=updateCanSave()},modifier = Modifier.fillMaxWidth(), isError = (userViewDto.email.isEmpty()), placeholder = { Text("Valide Mail eingeben") })
    TextField(value = userViewDto.gender, onValueChange = {userViewDto.gender = it;canSave=updateCanSave()},modifier = Modifier.fillMaxWidth(), isError = (userViewDto.gender.isEmpty()), placeholder = { Text("male|female") })
    Button(onClick = {  if (userViewDto.id == -1) {
        viewModel.addUser(userViewDto.toUserDto())
    } else {
        viewModel.updateUser(userViewDto.toUserDto())
    } }, enabled = canSave) {
        Text(text = stringResource(R.string.save))
    }
    if (userViewDto.id != -1) {
        Button(onClick = { viewModel.deleteUser(userViewDto.toUserDto()) }) {
            Text(text = stringResource(R.string.delete_user))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailPreview() {
    GoRestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            UserDetailView(userDto = UserDto(1, "Max Muster","max@muster.de", "male","active"),viewModel = UserDetailViewModel())
        }
    }
}