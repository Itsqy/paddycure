package com.darkcoder.paddycure.ui.home.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.ui.product.cart.ui.theme.fonts
import com.darkcoder.paddycure.ui.product.cart.ui.theme.greyColor
import com.darkcoder.paddycure.ui.product.cart.ui.theme.primColor
import com.darkcoder.paddycure.ui.product.cart.ui.theme.thirdColor
import com.darkcoder.paddycure.ui.product.cart.ui.theme.whiteColor
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun RecentNewsItem(photo: String, title: String, time: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 30.dp, vertical = 10.dp)
    ) {
        Row(Modifier.padding(start = 9.dp, top = 11.dp, bottom = 12.dp)) {

            Card(shape = RoundedCornerShape(8.dp)) {
                GlideImage( // CoilImage, FrescoImage
                    imageModel = { photo },
                    modifier = Modifier
                        .width(78.dp)
                        .height(67.dp),
                    component = rememberImageComponent {
                        // shows a shimmering effect when loading an image.
                        +ShimmerPlugin(
                            baseColor = Color.Gray,
                            highlightColor = thirdColor,
                            dropOff = 0.65f,
                            durationMillis = 350,
                            tilt = 20f
                        )
                    },

                    // shows an error text message when request failed.
                    failure = {
                        Text(
                            text = "image request failed.",
                            fontFamily = fonts,
                            textAlign = TextAlign.Center
                        )
                    })
            }
            Column(
                verticalArrangement = Arrangement.Center, modifier = Modifier.padding(start = 16.dp)
            ) {

                Text(
                    text = title,
                    fontSize = 10.sp,
                    fontFamily = fonts,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    modifier = Modifier.width(150.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_clock),
                        contentDescription = "clock icon"
                    )
                    Text(
                        text = time,
                        fontFamily = fonts,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        color = greyColor
                    )
                    Text(
                        text = "hr ago",
                        fontFamily = fonts,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        color = greyColor
                    )

                }

            }


            Button(

                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    primColor,
                ),
                modifier = Modifier
                    .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
                content = {
                    Text(

                        text = "Read",
                        color = whiteColor,
                        fontSize = 8.sp,
                        fontFamily = fonts,
                        fontWeight = FontWeight.Normal,

                        )
                }
            )

        }
    }

}