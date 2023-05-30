package com.darkcoder.paddycure.dummies

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(

    val id: String,
    val name: String,
    val img: String
) : Parcelable
