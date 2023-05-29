package com.darkcoder.paddycure.ui.scan.history.compose

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import com.darkcoder.paddycure.ui.product.cart.ui.theme.fonts
import com.darkcoder.paddycure.ui.product.cart.ui.theme.greyColor

@Composable
fun HistoryList() {
    LazyColumn {
        items(10) {
            HistoryItem()
        }
    }
}


@Composable
fun HistoryItem() {
    Card(
        modifier = Modifier
            .height(66.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
           ,
        shape = RoundedCornerShape(11.dp),
        colors = CardDefaults.cardColors(
            Color.White
        )


    ) {
        Row(
            modifier = Modifier
                .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                .padding(vertical = 23.dp, horizontal = 21.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "6 maret 2023",
                fontFamily = fonts,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                color = greyColor
            )
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_bullet),
                contentDescription = null,
                modifier = Modifier
                    .width(5.dp)
                    .height(5.dp)
            )
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Text(
                text = "09:00:01 WIB",
                fontFamily = fonts,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                color = greyColor
            )
            Spacer(modifier = Modifier.padding(end = 50.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_ios_arrow_next),
                contentDescription = null
            )

        }
    }
    Spacer(modifier = Modifier.padding(vertical = 10.dp))

}

@Preview(showBackground = true, backgroundColor = 0L, uiMode = UI_MODE_NIGHT_MASK )
@Composable
fun test() {

    MaterialTheme {
        HistoryList()
    }

}