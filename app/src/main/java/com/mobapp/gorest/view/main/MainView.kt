package com.mobapp.gorest.view.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobapp.gorest.R
import com.mobapp.gorest.view.main.cell.UserCell


@Composable
fun MainView(navController: NavController, mainViewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        mainViewModel.getAllUsers()
    }
    Column (modifier = Modifier.padding(horizontal = 5.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Button(onClick = { navController.navigate("userDetailView/-1") }) {
            Text(text = stringResource(R.string.add_new_user))
        }
        Column(modifier = Modifier.padding(top = 5.dp).fillMaxWidth()) {
            if (mainViewModel.loading) {
                CircularProgressIndicator()
            } else {
                MainList(navController, mainViewModel)
            }
        }
    }
}
@Composable
fun MainList(navController: NavController, mainViewModel: MainViewModel){
    if (mainViewModel.errorMessage.isEmpty()) { LazyColumn {
        itemsIndexed(items = mainViewModel.users) { index, item ->
            Box(Modifier.clickable(onClick = {
                navController.navigate("userDetailView/$index")
            })) {
                UserCell(userDto = item, mainViewModel.userImage)
            }
        }
    }
    } else {
        Text(text = mainViewModel.errorMessage, color = Color.Red, fontWeight = FontWeight.Bold) }
}
