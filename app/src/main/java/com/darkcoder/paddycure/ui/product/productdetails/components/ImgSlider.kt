package com.darkcoder.paddycure.ui.product.productdetails.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImgSlider(
) {
    val product = listOf(
        R.drawable.product1,
        R.drawable.product2,
        R.drawable.product3,
    )

    val scope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxWidth()
        .clip(RoundedCornerShape(20.dp))) {
        HorizontalPager(
        state = rememberPagerState(
            pageCount = product.size,
        )) { index ->
            Image(
                painter = painterResource(id = product[index]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
                    .height(245.dp)
            )
        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xffF4F4F4)
@Composable
fun ImgSliderPreview() {
    PaddycureTheme {
        ImgSlider()
    }
}