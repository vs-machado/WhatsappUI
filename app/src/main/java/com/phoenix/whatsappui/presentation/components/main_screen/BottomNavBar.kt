package com.phoenix.whatsappui.presentation.components.main_screen

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Update
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasMessages: Boolean,
    val badgeCount: Int? = null
)

@Composable
fun BottomNavBar() {

    val items = listOf(
        BottomNavigationItem(
            title = "Chats",
            selectedIcon = Icons.AutoMirrored.Filled.Message,
            unselectedIcon = Icons.AutoMirrored.Outlined.Message,
            badgeCount = 3,
            hasMessages = true
        ),
        BottomNavigationItem(
            title = "Updates",
            selectedIcon = Icons.Filled.Update,
            unselectedIcon = Icons.Outlined.Update,
            hasMessages = false
        ),
        BottomNavigationItem(
            title = "Communities",
            selectedIcon = Icons.Filled.People,
            unselectedIcon = Icons.Outlined.People,
            hasMessages = false
        ),
        BottomNavigationItem(
            title = "Calls",
            selectedIcon = Icons.Filled.Call,
            unselectedIcon = Icons.Outlined.Call,
            hasMessages = false
        )
    )

    val navigationBarLight = Color.White.toArgb()
    val navigationBarDark = Color(0xFF0B141B).toArgb()
    val isDarkMode = isSystemInDarkTheme()
    val context = LocalContext.current as ComponentActivity

    DisposableEffect(isDarkMode) {
        context.enableEdgeToEdge(
            navigationBarStyle = if (!isDarkMode) {
                SystemBarStyle.light(
                    navigationBarLight,
                    navigationBarDark
                )
            } else {
                SystemBarStyle.dark(navigationBarDark)
            }
        )

        onDispose {  }
    }


    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Column {
        HorizontalDivider(thickness = 0.6.dp, color = Color(0xFF142129))

        NavigationBar(containerColor = Color(0xFF0B141B)) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedItemIndex == index,
                    onClick = { selectedItemIndex = index },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 13.sp,
                            fontWeight = if(selectedItemIndex == index) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (item.badgeCount != null) {
                                    Badge(
                                        containerColor = Color(0xFF20D16E)
                                    ) {
                                        Text(
                                            text = item.badgeCount.toString(),
                                            color = Color(0xFF242B31)
                                        )
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFD9FED6),
                        selectedTextColor = Color.White,
                        indicatorColor = Color(0xFF103629),
                        unselectedIconColor = Color.White,
                        unselectedTextColor = Color.White
                    )
                )
            }
        }
    }
}