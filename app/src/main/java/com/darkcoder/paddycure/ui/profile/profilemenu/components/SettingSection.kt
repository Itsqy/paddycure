package com.darkcoder.paddycure.ui.profile.profilemenu.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme
import com.darkcoder.paddycure.ui.product.cart.ui.theme.fonts

@Composable
fun SettingSection() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Settings",
            fontFamily = fonts,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xff59981A),
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFE8F2D7), shape = RoundedCornerShape(15.dp))
                .padding(horizontal = 20.dp, vertical = 15.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier
                        .padding(vertical = 7.dp),
                    imageVector = Icons.Outlined.Settings,
                    tint = Color(0xff6F6F6F),
                    contentDescription = "Show more",
                )
                Spacer(modifier = Modifier.widthIn(15.dp))
                Text(
                    text = "Dark Mode",
                    fontFamily = fonts,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xff6F6F6F),
                )
            }
            Icon(
                modifier = Modifier
                    .padding(vertical = 7.dp)
                    .size(18.dp),
                imageVector = Icons.Outlined.ArrowForward,
                tint = Color(0xff6F6F6F),
                contentDescription = "Show more",
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFE8F2D7), shape = RoundedCornerShape(15.dp))
                .padding(horizontal = 20.dp, vertical = 15.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier
                        .padding(vertical = 7.dp),
                    imageVector = Icons.Outlined.Info,
                    tint = Color(0xff6F6F6F),
                    contentDescription = "Button info",
                )
                Spacer(modifier = Modifier.widthIn(15.dp))
                Text(
                    text = "About Application",
                    fontFamily = fonts,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xff6F6F6F),
                )
            }
            Icon(
                modifier = Modifier
                    .padding(vertical = 7.dp)
                    .size(18.dp),
                imageVector = Icons.Outlined.ArrowForward,
                tint = Color(0xff6F6F6F),
                contentDescription = "Show more",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingSectionPreview() {
    PaddycureTheme {
        SettingSection()
    }
}