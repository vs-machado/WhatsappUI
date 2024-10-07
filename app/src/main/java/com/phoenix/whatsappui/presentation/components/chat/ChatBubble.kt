package com.phoenix.whatsappui.presentation.components.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.phoenix.whatsappui.R

@Composable
fun ChatBubble(
    userName: String,
    message: String,
    photoId: Int,
    isSender: Boolean,
    isGroup: Boolean,
    messageHour: String,
){
    val context = LocalContext.current
    val drawable = ContextCompat.getDrawable(context, photoId)
    val bitmap = drawable?.toBitmap(width = 100, height = 100)  // Resize to 100x100
    val resizedBitmap = bitmap?.asImageBitmap()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if(isSender) Arrangement.End else Arrangement.Start
    ){
        if (!isSender){
            resizedBitmap?.let{ imageBitmap ->
                Image(
                    bitmap = imageBitmap,
                    contentDescription = "Profile image",
                    alignment = Alignment.TopStart,
                    modifier = Modifier
                        .size(24.dp)
                        .offset(y = 2.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(
                modifier = Modifier.padding(4.dp)
            )
        }

        Box(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .background(
                    color = if (isSender) Color(0xFF134D37) else Color(0xFF1F2C34),
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(start = 8.dp, top = 4.dp, end = 8.dp)
        ) {
            Column {
                if (!isSender && isGroup) {
                    Text(
                        text = userName,
                        color = Color.Green,
                        modifier = Modifier.padding(start = 4.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(
                    text = message,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 4.dp, end = 48.dp),
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = messageHour,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.End)
                )
            }
        }

    }
}

@Preview
@Composable
fun PreviewChatBubble(){
    ChatBubble(
        "Jane Doe",
        message = "Hello!",
        R.drawable.man, // Doesn't need another picture
        isSender = true,
        isGroup = false,
        messageHour = "5:32 PM"
    )
    ChatBubble(
        "John Doe",
        message = "Hello!",
        R.drawable.man,
        isSender = false,
        isGroup = false,
        messageHour = "5:32 PM"
    )
}