package com.darkcoder.paddycure.ui.product.shop.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
fun ListProduct() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xffF4F4F4))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "All Product",
                fontFamily = fonts,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xff59981A)
            )
            Box(modifier = Modifier
                .width(30.dp)
                .height(2.dp)
                .background(
                    color = Color(0xffD8E84D),
                    shape = RoundedCornerShape(10.dp)
                )
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        ItemProduct()
    }
}

@Preview(showBackground = true)
@Composable
fun ListProductPreview() {
    PaddycureTheme {
        ListProduct()
    }
}

@Composable
fun ItemProduct() {
    LazyVerticalGrid(
        modifier = Modifier.height(2000.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Adaptive(minSize = 140.dp),
    ) {
        items(10) {
            Card (
                modifier = Modifier.width(140.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
            ){
                Column {
                    Image(
                        painter = painterResource(R.drawable.bg_result),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(6.dp)
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Column(modifier = Modifier.padding(8.dp)){
                        Text(
                            text = "Iprodione Tablet",
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xff344B06)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Rp. ",
                                    fontFamily = fonts,
                                    fontSize = 7.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xffA6A4A4)
                                )
                                Text(
                                    text = "150.000",
                                    fontFamily = fonts,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xffA6A4A4)
                                )
                            }
                            Image(
                                painter = painterResource(R.drawable.ic_forward),
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
}
