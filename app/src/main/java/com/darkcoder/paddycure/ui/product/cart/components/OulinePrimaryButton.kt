package com.darkcoder.paddycure.ui.product.cart.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme
import com.darkcoder.paddycure.ui.product.cart.ui.theme.fonts

@Composable
fun OutlinePrimaryButton(title: String, modifier: Modifier) {
    OutlinedButton(
        onClick = { /*TODO*/ },
        border = BorderStroke(color = Color(0xff81B622), width = 2.dp),
        modifier = modifier
    ) {
        Text(
            text = title,
            fontFamily = fonts,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xff81B622),
            modifier = Modifier
                .padding(horizontal = 5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OutlinePrimaryButtonPreview() {
    PaddycureTheme {
        OutlinePrimaryButton("Check Out Now!",
            modifier = Modifier.padding(vertical = 10.dp)
                .fillMaxWidth()
                .heightIn(50.dp))
    }
}