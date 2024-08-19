package com.phoenix.whatsappui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Update
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phoenix.whatsappui.domain.model.MessageList
import com.phoenix.whatsappui.ui.theme.WhatsappUITheme

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasMessages: Boolean,
    val badgeCount: Int? = null
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatsappUITheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){
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
                .padding(innerPadding)
                .background(Color(0xFF0B141B))
        ) {
            Column {
                ChatList(chatItems)
            }
        }
    }
}

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
fun ChatList(chatItems: List<MessageList>){
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
            ChatListItem(messageList = chatItem)
        }
    }
}

@Composable
fun ChatListItem(messageList: MessageList){
    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 16.dp)
            .fillMaxWidth()
    ) {
        ProfileImage(photoId = messageList.photoId)

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
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
                    color = Color.White
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
        painter = painterResource(id = photoId),
        contentDescription = "Profile image"
    )
}

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

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    NavigationBar(containerColor = Color(0xFF0B141B)) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = { selectedItemIndex = index },
                label = {
                    Text(
                        text = item.title,
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

@Preview(showBackground = true)
@Composable
fun Preview() {
   MainScreen()
}

