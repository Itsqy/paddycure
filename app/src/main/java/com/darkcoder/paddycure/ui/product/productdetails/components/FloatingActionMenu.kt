package com.example.trialcapstone.components.DetailProduct

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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

@Composable
fun FloatingActionMenu(
) {
    BottomAppBar(
        containerColor = Color.Transparent,
        modifier = Modifier
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xff59981A), shape = RoundedCornerShape(50))
                .padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(alignment = Alignment.CenterStart),
                text = "Rp. 150.000",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
                    .defaultMinSize(minWidth = 140.dp, minHeight = 1.dp)
                    .padding(vertical = 7.dp),
                onClick = {},
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = "Add to Cart",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xff59981A)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffF4F4F4)
@Composable
fun FloatingActionMenuPreview() {
    PaddycureTheme {
        FloatingActionMenu()
    }
}