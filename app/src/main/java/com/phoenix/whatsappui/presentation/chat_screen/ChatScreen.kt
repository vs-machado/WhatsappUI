package com.phoenix.whatsappui.presentation.chat_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phoenix.whatsappui.R
import com.phoenix.whatsappui.domain.model.ChatMessage
import com.phoenix.whatsappui.presentation.components.chat.ChatBubble
import com.phoenix.whatsappui.presentation.components.chat.ChatToolbar
import com.phoenix.whatsappui.presentation.components.chat.MessageInput

@Composable
fun ChatScreen() {
    val chatMessages = remember {
        listOf(
            ChatMessage(message = "Hello!", userName =  "John Doe", photoId = R.drawable.man, isSender = false, isGroup = false, messageHour = "5:32 PM"),
            ChatMessage(message = "Hello! How are you doing?", userName = "Jane Doe", photoId = R.drawable.man, isSender = true, isGroup = false, messageHour = "5:37 PM"),
            ChatMessage(message = "I'm doing fine :)", userName = "John Doe", photoId = R.drawable.man, isSender = false, isGroup = false, messageHour = "5:39 PM"),
        ).sortedBy { it.messageHour }

    }

    Scaffold(
        topBar = {
            ChatToolbar(
                photoId = R.drawable.man,
                title = "John Doe",
                icons = listOf(Icons.Outlined.CameraAlt, Icons.Outlined.Phone, Icons.Outlined.MoreVert)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .fillMaxSize()
                .background(color = Color(0xFF0B141B))
        ) {
            HorizontalDivider(
                color = Color.White,
                thickness = 1.dp,
                modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
            )
            Image(
                painter = painterResource(id = R.drawable.whats_background),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .drawWithContent {
                        drawContent()
                        drawRect(
                            color = Color(0xFF0B141B).copy(alpha = 0.85f),
                            size = size
                        )
                    }
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
                    .padding(innerPadding)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                LazyColumn {
                    items(chatMessages) { message ->
                        ChatBubble(
                            userName = message.userName,
                            message = message.message,
                            photoId = message.photoId,
                            isSender = message.isSender,
                            isGroup = message.isGroup,
                            messageHour = message.messageHour
                        )
                    }
                }
                MessageInput()
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    ChatScreen()
}