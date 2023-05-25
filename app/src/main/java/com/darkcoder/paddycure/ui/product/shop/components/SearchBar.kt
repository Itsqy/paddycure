package com.darkcoder.paddycure.ui.product.shop.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme
import com.darkcoder.paddycure.ui.product.cart.ui.theme.fonts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            value = "",
            onValueChange = {},
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color(0xffA6A4A4),
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(
                    stringResource(R.string.placeholder_search),
                    color = Color(0xffA6A4A4),
                    fontFamily = fonts
                )
            },
            modifier = modifier
                .heightIn(min = 48.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box {
            Button(
                modifier = Modifier.defaultMinSize(minWidth = 50.dp, minHeight = 1.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                onClick = { }) {
                modifier
                    .padding(16.dp)
                    .heightIn(min = 48.dp)
                    .size(55.dp)
                Icon(
                    modifier = Modifier
                        .padding(vertical = 7.dp),
                    imageVector = Icons.Rounded.ShoppingCart,
                    tint = Color(0xff59981A),
                    contentDescription = "Show more",
                )
            }
            Canvas(
                modifier = Modifier
                    .size(size = 18.dp)
                    .align(Alignment.TopEnd)
                    .offset(y = -5.dp)
            ) {
                drawCircle(
                    color = Color(0xffD9E84E)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffF4F4F4)
@Composable
fun SearchBarPreview() {
    PaddycureTheme {
        SearchBar()
    }
}