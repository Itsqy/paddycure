package com.darkcoder.paddycure.ui.product.cart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.ui.product.cart.components.Bills
import com.darkcoder.paddycure.ui.product.cart.components.ListCart
import com.darkcoder.paddycure.ui.product.cart.components.PrimaryButton
import com.darkcoder.paddycure.ui.product.cart.components.TopBar
import com.darkcoder.paddycure.ui.product.cart.ui.theme.PaddycureTheme

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaddycureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Cart()
                }
            }
        }
    }
}

@Composable
fun Cart() {

    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
    ) {
        item {
            TopBar("Your Cart")
            Spacer(modifier = Modifier.height(35.dp))
        }
        items(4) {
            ListCart("Fungicides Propiconazole non Tablet", R.drawable.bg_result, 280000, 1)
            Spacer(modifier = Modifier.height(15.dp))
            Canvas(
                Modifier
                    .fillMaxWidth()
                    .height(5.dp)) {

                drawLine(
                    color = Color(0xffA6A4A4),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    pathEffect = pathEffect
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
        item{
            Spacer(modifier = Modifier.height(20.dp))
            Bills()
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            PrimaryButton(title = "Check Out Now!", modifier = Modifier.padding(vertical = 10.dp)
                .fillMaxWidth()
                .heightIn(50.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartPreview() {
    PaddycureTheme {
        Cart()
    }
}