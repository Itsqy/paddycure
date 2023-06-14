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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.data.model.remote.BeritaResponseItem
import com.darkcoder.paddycure.ui.product.cart.ui.theme.fonts
import com.darkcoder.paddycure.ui.product.cart.ui.theme.greyColor
import com.darkcoder.paddycure.ui.product.cart.ui.theme.primColor
import com.darkcoder.paddycure.ui.product.cart.ui.theme.thirdColor
import com.darkcoder.paddycure.ui.product.cart.ui.theme.whiteColor
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun TopNewsList(newsData: ArrayList<BeritaResponseItem>) {

    Column() {

        LazyRow() {
            items(newsData, key = { it.id }) {
                TopNewsItem(photo = it.imgBerita, title = it.judulBerita)

            }

        }


    }


}



@Composable
fun TopNewsItem(photo: String, title: String) {


    Column(
        modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.Start

    ) {
        Card(shape = RoundedCornerShape(8.dp), modifier = Modifier.width(133.dp)) {

            Column(modifier = Modifier.padding(6.dp)) {
                Card(shape = RoundedCornerShape(8.dp)) {
                    GlideImage( // CoilImage, FrescoImage
                        imageModel = { photo },
                        modifier = Modifier
                            .width(122.dp)
                            .height(80.dp),
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

                Text(
                    modifier = Modifier

                        .width(133.dp)
                        .padding(top = 9.dp),
                    text = title,

                    fontFamily = fonts,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }

        }


    }

}



