package com.darkcoder.paddycure.ui.product.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme
import com.darkcoder.paddycure.ui.product.cart.ui.theme.fonts

@Composable
fun ListCart(title: String, img: Int, cost: Int, count: Int) {

    Card (
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ){
        Row(
            horizontalArrangement = Arrangement.Start,
        ) {
            Image(
                painter = painterResource(img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(6.dp)
                    .width(100.dp)
                    .height(70.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(modifier = Modifier.padding(8.dp)){
                Text(
                    text = title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = fonts,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xff344B06)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Rp. $cost",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xffA6A4A4)
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_minus_outline),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .selectable(
                                    true,
                                    onClick = {}
                                )
                        )
                        Text(
                            text = count.toString(),
                            fontFamily = fonts,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xff5C5C5C),
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.ic_plus_outline),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .selectable(
                                    true,
                                    onClick = {}
                                )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListCartPreview() {
    PaddycureTheme {
        ListCart("Fungicides Propiconazole non Tablet", R.drawable.bg_result, 280000, 1)
    }
}