package com.darkcoder.paddycure.ui.product.cart.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme
import com.darkcoder.paddycure.ui.product.cart.ui.theme.fonts

@Composable
fun Bills() {

    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFF4F8EB), shape = RoundedCornerShape(15.dp))
            .padding(20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Shipping Total",
                    fontFamily = fonts,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xff5B6C6A),
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Platform Total",
                    fontFamily = fonts,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xff5B6C6A),
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "Rp. 158.000",
                    fontFamily = fonts,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xff5B6C6A),
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Rp. 1.000",
                    fontFamily = fonts,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xff5B6C6A),
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Canvas(
            Modifier
                .fillMaxWidth()
                .height(5.dp)
                .padding(5.dp)) {

            drawLine(
                color = Color(0xffA6A4A4),
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                pathEffect = pathEffect
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Payment Total",
                fontFamily = fonts,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xff5B6C6A),
                modifier = Modifier
                    .padding(horizontal = 5.dp)
            )
            Text(
                text = "Rp. 158.000",
                fontFamily = fonts,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xff038035),
                modifier = Modifier
                    .padding(horizontal = 5.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BillsPreview() {
    PaddycureTheme {
        Bills()
    }
}