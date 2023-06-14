package com.darkcoder.paddycure.ui.scan.history.compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
fun HistoryItem(date: String, onitemClick: () -> Unit) {

    Log.d("testHistoryAdapter", "onViewCreated:$onitemClick ")
    Button(
        onitemClick,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(Color.White),
        shape = RoundedCornerShape(11.dp),
        colors = androidx.compose.material.ButtonDefaults.buttonColors(Color.White),


        ) {
        Row(
            modifier = Modifier
                .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                .padding(vertical = 23.dp, horizontal = 21.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = date,
                fontFamily = fonts,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.padding(end = 100.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_ios_arrow_next),
                contentDescription = null
            )

        }
    }
    Spacer(modifier = Modifier.padding(vertical = 50.dp))

}

@Preview(showBackground = true)
@Composable
fun test() {
    MaterialTheme() {

        HistoryItem(date = "23 maret 2023") {

        }

    }

}

