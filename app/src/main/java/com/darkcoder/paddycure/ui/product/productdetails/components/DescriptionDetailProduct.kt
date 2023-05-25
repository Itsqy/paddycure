package com.example.trialcapstone.components.DetailProduct

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme

@Composable
fun DescriptionDetailProduct(
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Description",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xff344B06)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi...",
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xffA6A4A4)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xffF4F4F4)
@Composable
fun DescriptionDetailProductPreview() {
    PaddycureTheme {
        DescriptionDetailProduct()
    }
}