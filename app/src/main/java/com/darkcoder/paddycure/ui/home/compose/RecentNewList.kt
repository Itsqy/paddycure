package com.darkcoder.paddycure.ui.home.compose

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkcoder.paddycure.dummies.HeroesData
import com.darkcoder.paddycure.ui.product.cart.ui.theme.fonts
import com.darkcoder.paddycure.ui.product.cart.ui.theme.primColor

@Composable
fun RecentNewsList() {
//    Text(
//
//        text = "Recently News",
//        color = primColor,
//        fontSize = 20.sp,
//        fontFamily = fonts,
//        fontWeight = FontWeight.SemiBold,
//        modifier = Modifier.padding(start = 25.dp)
//    )
    LazyColumn(Modifier.height(300.dp)) {
        items(HeroesData.heroes, key = { it.id }) {

            RecentNewsItem(photo = it.img, title = it.name)


        }
    }
}