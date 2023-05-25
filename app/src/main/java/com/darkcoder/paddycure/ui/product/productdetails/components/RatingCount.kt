package com.example.trialcapstone.components.DetailProduct

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme
import com.darkcoder.paddycure.ui.product.cart.ui.theme.fonts

@Composable
fun RatingCount(
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
            .background(
                color = Color(0xff344B06),
                shape = RoundedCornerShape(30.dp)
            )
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp),
            ) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Star",
                    tint = Color(0xffD8E84D),
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "275 Terjual",
                    fontFamily = fonts,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_minus_outline),
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp)
                    .selectable(
                        true,
                        onClick = {}
                    )
            )
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = "1",
                fontFamily = fonts,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xff59981A)
            )
            Image(
                painter = painterResource(R.drawable.ic_plus_outline),
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp)
                    .selectable(
                        true,
                        onClick = {}
                    )
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffF4F4F4)
@Composable
fun RatingCountPreview() {
    PaddycureTheme {
        RatingCount()
    }
}