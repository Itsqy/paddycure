package com.darkcoder.paddycure.ui.profile.profileedit.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme
import com.darkcoder.paddycure.ui.product.cart.ui.theme.fonts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomEditText(label: String, placeholder: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            fontFamily = fonts,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xff59981A),
        )
        Spacer(modifier = Modifier.heightIn(10.dp))
        TextField(
            value = "",
            onValueChange = {},
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(
                    placeholder,
                    color = Color(0xff81B622),
                    fontFamily = fonts
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 55.dp)
                .border(width = 2.dp, color = Color(0xff81B622), shape = RoundedCornerShape(50.dp))
                .padding(horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomEditTextPreview() {
    PaddycureTheme {
        CustomEditText("Full Name", "PaddyCure")
    }
}