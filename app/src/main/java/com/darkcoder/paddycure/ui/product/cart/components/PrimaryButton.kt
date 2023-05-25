package com.darkcoder.paddycure.ui.product.cart.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
fun PrimaryButton(title: String, modifier: Modifier) {
    Button(
        onClick = { /*TODO*/ },
        elevation = ButtonDefaults.buttonElevation(2.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff59981A)),
        modifier = modifier
    ) {
        Text(
            text = title,
            fontFamily = fonts,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonPreview() {
    PaddycureTheme {
        PrimaryButton("Check Out Now!",
            modifier = Modifier.padding(vertical = 10.dp)
            .fillMaxWidth()
            .heightIn(50.dp))
    }
}