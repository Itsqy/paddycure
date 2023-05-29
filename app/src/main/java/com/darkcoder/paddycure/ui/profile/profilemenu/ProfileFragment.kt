package com.darkcoder.paddycure.ui.profile.profilemenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.darkcoder.paddycure.ui.product.cart.components.OutlinePrimaryButton
import com.darkcoder.paddycure.ui.product.cart.components.TopBar
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme
import com.darkcoder.paddycure.ui.product.cart.ui.theme.fonts
import com.darkcoder.paddycure.ui.profile.profilemenu.components.ProfileInformation
import com.darkcoder.paddycure.ui.profile.profilemenu.components.SettingSection

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                PaddycureTheme {
                    // A surface container using the 'background' color from the theme
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
        OutlinePrimaryButton(
            title = "Logout", modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .heightIn(50.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    PaddycureTheme {
        Profile()
    }
}