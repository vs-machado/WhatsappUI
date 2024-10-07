package com.phoenix.whatsappui.presentation.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.phoenix.whatsappui.R
import com.phoenix.whatsappui.domain.model.MessageList
import com.phoenix.whatsappui.presentation.components.main_screen.BottomNavBar
import com.phoenix.whatsappui.presentation.components.main_screen.TopBar

@Composable
fun MainScreen(
    onNavigateToChat: () -> Unit
){
    val chatItems = remember {
        listOf(
            MessageList(name = "John Doe", message = "Hey, how are you?", time = "14:30", photoId = R.drawable.man),
//            MessageList(name = "Jane Doe", message = "I'm good, thanks!", time = "14:31", photoId = R.drawable.woman),
//            MessageList(name = "Family Group", message = "Claire: Let's have dinner tonight.", time = "19:00", R.drawable.family)
        )
    }
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*Start a new conversation*/ },
                containerColor = Color(0xFF21C063),
                contentColor = Color(0xFF0B141B)
            ) {
                Icon(Icons.AutoMirrored.Filled.Message, contentDescription = "Start conversation")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0B141B))
                .padding(innerPadding)
        ) {
            Column {
                ChatList(
                    chatItems,
                    onChatItemClick = { onNavigateToChat() }
                )
            }
        }
    }
}

@Composable
fun SearchBar(onSearchTextChanged: (String) -> Unit){
    var searchText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp) // Adjusted vertical padding
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFF242B31)) // Background color
            .border(1.dp, Color(0xFF121B22), RoundedCornerShape(24.dp)) // Border color and shape
            .height(48.dp)
    ) {
        BasicTextField(
            value = searchText,
            onValueChange = { newText ->
                searchText = newText
                onSearchTextChanged(newText)
            },
            singleLine = true,
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
            ),
            cursorBrush = SolidColor(Color(0xFF20D16E)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 44.dp, end = 8.dp)
                .align(Alignment.CenterStart)// Adjust to avoid overlapping with icon
        )

        if(searchText.isEmpty()){
            Text(
                text = "Search...",
                color = Color(0xFF70777B), // Hint color
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 44.dp, top = 14.dp)
                    .height(48.dp) // Ensure hint text aligns with the field height
            )
        }

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = Color(0xFF70777B),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
                .size(22.dp) // Adjust size if needed
        )
    }
}

@Composable
fun ChatList(
    chatItems: List<MessageList>,
    onChatItemClick: (MessageList) -> Unit
){
    var searchQuery by remember { mutableStateOf("") }

    SearchBar { newQuery ->
        searchQuery = newQuery
    }
    LazyColumn{
        val filteredList = chatItems.filter{ item ->
            item.name.contains(searchQuery, ignoreCase = true) ||
                    item.message.contains(searchQuery, ignoreCase = true)
        }

        items(filteredList) { chatItem ->
            ChatListItem(
                messageList = chatItem,
                onClick = { onChatItemClick(chatItem) }
            )
        }
    }
}

@Composable
fun ChatListItem(
    messageList: MessageList,
    onClick: () -> Unit
){
    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        ProfileImage(photoId = messageList.photoId)

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
                .align(Alignment.CenterVertically)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = messageList.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = messageList.time,
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .align(Alignment.CenterVertically)
                )

            }
            Text(
                text = messageList.message,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun ProfileImage(photoId: Int){
    Image(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape),
        painter = rememberAsyncImagePainter(model = photoId),
        contentDescription = "Profile image"
    )
}
