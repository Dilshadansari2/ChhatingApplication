package com.example.chapapplication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chapapplication.features.Home.HomeScreen
import com.example.chapapplication.features.auth.signin.SignInScreen
import com.example.chapapplication.features.auth.signup.SignUpScreen
import com.example.chapapplication.features.chat.ChatScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MainApp(){
Surface(modifier = Modifier.fillMaxSize()) {
    val navController = rememberNavController()
    val currentUser = FirebaseAuth.getInstance().currentUser
    val start = if (currentUser == null) "home" else "login"
    NavHost(navController = navController, startDestination = "login") {


        composable("login"){
        SignInScreen(navController)
        }
        composable("signup") {
            SignUpScreen(navController)
        }
        composable("home"){
            HomeScreen(navController)
        }
        composable("chat/{channelId}", arguments = listOf(
            navArgument("channelId"){
                type = NavType.StringType
            }
        )){
            val channelId = it.arguments?.getString("channelId") ?: ""
            ChatScreen(navController,channelId)
        }
        }
}
}