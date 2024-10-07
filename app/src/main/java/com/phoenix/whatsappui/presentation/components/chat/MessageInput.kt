package com.phoenix.whatsappui.presentation.components.chat

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.outlined.AttachFile
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.CurrencyBitcoin
import androidx.compose.material.icons.outlined.EmojiEmotions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MessageInput() {
    val messageTextState = remember { mutableStateOf(TextFieldValue("")) }

    Row(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(8.dp)
            .heightIn(max = 54.dp)
    ) {
        TextField(
            value = messageTextState.value,
            onValueChange = { messageTextState.value = it },
            shape = RoundedCornerShape(32.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF1F2C34),
                focusedContainerColor = Color(0xFF1F2C34),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color(0xFF20D16E)
            ),
            modifier = Modifier
                .weight(1f)
                .background(Color.Transparent, RoundedCornerShape(8.dp)),
            placeholder = { Text(text = "Message", color = Color(0xFF8F9498))},
            leadingIcon = {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(start = 4.dp)
                ) {
                    Icon (
                        imageVector = Icons.Outlined.EmojiEmotions,
                        contentDescription = "Emojis",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                Row {
                    IconButton(onClick = {}) {
                        Icon (
                            imageVector = Icons.Outlined.AttachFile,
                            contentDescription = "Send",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon (
                            imageVector = Icons.Outlined.CurrencyBitcoin,
                            contentDescription = "Send",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {}, modifier = Modifier.padding(end = 4.dp)) {
                        Icon (
                            imageVector = Icons.Outlined.CameraAlt,
                            contentDescription = "Open camera",
                            tint = Color.White
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .background(color = Color(0xFF21C063), shape = CircleShape)
        ) {
            AnimatedContent(
                targetState = messageTextState.value.text.isEmpty(),
                transitionSpec = {
                    fadeIn(animationSpec = tween(200)) togetherWith
                            fadeOut(animationSpec = tween(200))
                }, label = ""
            ) { isEmpty ->
                if (isEmpty) {
                    Icon(
                        imageVector = Icons.Filled.Mic,
                        contentDescription = "Record audio",
                        tint = Color.Black
                    )
                } else {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send message",
                        tint = Color.Black
                    )
                }
            }
        }

    }

}

@Composable
@Preview
fun MessageInputPreview() {
    MessageInput()
}