package com.phoenix.whatsappui.presentation.components.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phoenix.whatsappui.R

@Composable
fun TopBar(){
    val windowInsets = WindowInsets.systemBars
    val topBarPadding = with(LocalDensity.current){
        windowInsets.getTop(LocalDensity.current).toDp()
    }
    Row(
        modifier = Modifier
            .background(color = Color(0xFF0B141B))
            .padding(
                start = 16.dp,
                top = topBarPadding + 14.dp,
                end = 16.dp,
                bottom = 14.dp
            )
    ){
        Text(
            "WhatsApp 2",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Bottom)
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically)
                .clickable {/*Open camera*/ }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.outline_camera_alt_24),
                contentDescription = "Open camera",
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Box(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically)
                .clickable {/*Open camera*/ }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_more_vert_24),
                contentDescription = "Open camera",
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }

    }
}