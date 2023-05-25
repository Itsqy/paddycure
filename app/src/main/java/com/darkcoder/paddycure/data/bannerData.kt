package com.darkcoder.paddycure.data

import com.darkcoder.paddycure.R

data class BannerData(
    val title :String,
    val button : String,
    val desc :String,
    val imgUri:Int
)

val bannerList = listOf(
    BannerData(
        "Sitting Pretty",
        "Shop Now",
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.bg_result
    ),
    BannerData(
        "Love her Expression",
        "Shop Now",
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.bg_result
    ),
    BannerData(
        "Childhood Superman",
        "Shop Now",
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.bg_result
    ),

)
