package com.darkcoder.paddycure.ui.profile.profileedit

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.ui.product.cart.components.PrimaryButton
import com.darkcoder.paddycure.ui.product.cart.components.TopBar
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme
import com.darkcoder.paddycure.ui.profile.profileedit.components.CustomAvatar
import com.darkcoder.paddycure.ui.profile.profileedit.components.CustomEditText

class ProfileEditActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaddycureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileEdit()
                }
            }
        }
    }
}

@Composable
fun ProfileEdit() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        TopBar("Edit Profile")
        Spacer(modifier = Modifier.height(40.dp))
        CustomAvatar(R.drawable.ic_camera_green)
        Spacer(modifier = Modifier.height(35.dp))
        CustomEditText(label = "Full Name", placeholder = "PaddyCure")
        Spacer(modifier = Modifier.height(20.dp))
        CustomEditText(label = "Email", placeholder = "paddycure@gmail.com")
        Spacer(modifier = Modifier.height(20.dp))
        CustomEditText(label = "Password", placeholder = "**********")
        Spacer(modifier = Modifier.height(20.dp))
        CustomEditText(label = "New Password", placeholder = "")
        Spacer(modifier = Modifier.height(40.dp))
        PrimaryButton(title = "Save", modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .heightIn(55.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileEditPreview() {
    PaddycureTheme {
        ProfileEdit()
    }
}