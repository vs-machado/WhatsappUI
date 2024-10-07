package com.phoenix.whatsappui.presentation.components.chat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.phoenix.whatsappui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatToolbar(photoId: Int, title: String, icons: List<ImageVector>) {

    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = { /*popbackstack*/ }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Replace with actual drawable
                    contentDescription = "Back"
                )
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(photoId)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        },
        actions = {
            icons.forEach { iconId ->
                IconButton(onClick = { /* Handle icon click */ }) {
                    Icon(
                        imageVector = iconId,
                        contentDescription = "Icon",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0B141B))
    )
}

@Preview
@Composable
fun Preview() {
    ChatToolbar(
        R.drawable.man,
        "John Doe",
        listOf(Icons.Outlined.CameraAlt, Icons.Outlined.Phone, Icons.Outlined.MoreVert)
    )
}