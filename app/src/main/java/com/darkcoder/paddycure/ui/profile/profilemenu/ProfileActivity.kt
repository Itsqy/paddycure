package com.darkcoder.paddycure.ui.profile.profilemenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkcoder.paddycure.ui.product.cart.components.OutlinePrimaryButton
import com.darkcoder.paddycure.ui.product.cart.components.TopBar
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme
import com.darkcoder.paddycure.ui.product.cart.ui.theme.fonts
import com.darkcoder.paddycure.ui.profile.profilemenu.components.ProfileInformation
import com.darkcoder.paddycure.ui.profile.profilemenu.components.SettingSection

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaddycureTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Profile()
                }
            }
        }
    }
}

@Composable
fun Profile() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        TopBar("Profile")
        Spacer(modifier = Modifier.height(30.dp))
        ProfileInformation()
        Spacer(modifier = Modifier.height(30.dp))
        SettingSection()
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Settings",
            fontFamily = fonts,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xff59981A),
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinePrimaryButton(title = "Logout", modifier = Modifier.padding(vertical = 10.dp)
            .fillMaxWidth()
            .heightIn(50.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    PaddycureTheme {
        Profile()
    }
}