package com.darkcoder.paddycure.ui.profile.profilemenu.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.ui.product.cart.components.PrimaryButton
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme
import com.darkcoder.paddycure.ui.product.cart.ui.theme.fonts

@Composable
fun ProfileInformation() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFE8F2D7), shape = RoundedCornerShape(15.dp))
            .padding(20.dp),
    ) {
        Spacer(modifier = Modifier.height(9.dp))
        Image(
            painter = painterResource(id = R.drawable.bg_result),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(90.dp),
        )
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = "PaddyCure Team",
            fontFamily = fonts,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xff464646),
        )
        Text(
            text = "paddycure@gmail.com",
            fontFamily = fonts,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xff6F6F6F),
        )
        Spacer(modifier = Modifier.height(20.dp))
        PrimaryButton(title = "Edit Profile", modifier = Modifier.padding(vertical = 10.dp)
            .widthIn(140.dp)
            .heightIn(50.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileInformationPreview() {
    PaddycureTheme {
        ProfileInformation()
    }
}