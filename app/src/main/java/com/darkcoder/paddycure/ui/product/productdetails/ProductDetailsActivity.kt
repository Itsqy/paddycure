package com.darkcoder.paddycure.ui.product.productdetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkcoder.paddycure.ui.product.cart.components.TopBar
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme
import com.darkcoder.paddycure.ui.product.productdetails.components.ImgSlider
import com.example.trialcapstone.components.DetailProduct.DescriptionDetailProduct
import com.example.trialcapstone.components.DetailProduct.FloatingActionMenu
import com.example.trialcapstone.components.DetailProduct.ProductRecommendation
import com.example.trialcapstone.components.DetailProduct.RatingCount

class ProductDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaddycureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductDetails()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetails() {
    Scaffold(
        containerColor = Color(0xffF4F4F4),
        bottomBar = { FloatingActionMenu() }
    ) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            TopBar("Product Details")
            Spacer(modifier = Modifier.height(30.dp))

            ImgSlider()

            Text(modifier = Modifier
                .padding(top = 23.dp, bottom = 20.dp),
                text = "Long Title Product here Max 2 Line Okey Okey", //pindah ke string
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xff5C5C5C)
            )

            RatingCount()
            Spacer(modifier = Modifier.height(20.dp))

            DescriptionDetailProduct()
            Spacer(modifier = Modifier.height(20.dp))

            ProductRecommendation()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailsPreview() {
    PaddycureTheme {
        ProductDetails()
    }
}