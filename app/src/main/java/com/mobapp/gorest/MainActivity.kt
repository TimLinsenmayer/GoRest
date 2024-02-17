package com.mobapp.gorest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mobapp.gorest.dto.UserDto
import com.mobapp.gorest.ui.theme.GoRestTheme
import com.mobapp.gorest.view.main.MainView
import com.mobapp.gorest.view.main.MainViewModel
import com.mobapp.gorest.view.userDetail.UserDetailView
import com.mobapp.gorest.view.userDetail.UserDetailViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoRestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GoRestApp()
                }
            }
        }
    }
}

@Composable
fun GoRestApp() {
    val navController = rememberNavController()
    val mainViewModel = viewModel<MainViewModel>()
    val userDetailViewModel = viewModel<UserDetailViewModel>()
    userDetailViewModel.navController = navController
    NavHost(navController, startDestination =
    "mainView") {
        composable(route = "mainView") {
            MainView(navController=navController, mainViewModel = mainViewModel)
        }
        composable("userDetailView/{index}",arguments = listOf(navArgument("index") { type = NavType.IntType })) {
            backStackEntry ->
            var index = backStackEntry.arguments?.getInt("index") ?: return@composable
            if (index == -1) {
                mainViewModel.users.add(
                    UserDto(-1, "", "", "", "active")
                )
                index = mainViewModel.users.count() - 1
            }
            val user = mainViewModel.users[index]
            UserDetailView(user, userDetailViewModel)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    GoRestTheme {
        GoRestApp()
    }
}