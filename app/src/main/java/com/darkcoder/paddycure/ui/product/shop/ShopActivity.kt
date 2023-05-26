package com.darkcoder.paddycure.ui.product.shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme
import com.darkcoder.paddycure.ui.product.shop.components.ListProduct
import com.darkcoder.paddycure.ui.product.shop.components.SearchBar
import com.darkcoder.paddycure.ui.product.shop.components.ViewPagerSlider
import com.google.accompanist.pager.ExperimentalPagerApi

class ShopActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaddycureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Shop()
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Shop() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(color = Color(0xffF4F4F4))
            .padding(16.dp)
    ) {
        SearchBar(
            modifier = Modifier.clickable {  }
        )
        Spacer(modifier = Modifier.height(30.dp))

        ViewPagerSlider()
        ListProduct()
    }
}

@Preview(showBackground = true)
@Composable
fun ShopPreview() {
    PaddycureTheme {
        Shop()
    }
}