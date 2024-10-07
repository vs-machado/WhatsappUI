package com.phoenix.whatsappui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phoenix.whatsappui.presentation.chat_screen.ChatScreen
import com.phoenix.whatsappui.presentation.main_screen.MainScreen
import com.phoenix.whatsappui.ui.theme.WhatsappUITheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatsappUITheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Home,
                    modifier = Modifier.background(Color(0xFF0B141B))
                ) {
                    composable<Home>{
                        MainScreen(onNavigateToChat = { navController.navigate(Chat) })
                    }
                    composable<Chat>(
                        enterTransition = { slideInVertically(initialOffsetY = { it }) },
                        exitTransition = { slideOutVertically(targetOffsetY = { it }) },
                        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
                        popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
                    ) {
                        ChatScreen()
                    }
                }
            }
        }
    }

    @Serializable
    object Home

    @Serializable
    object Chat
}


