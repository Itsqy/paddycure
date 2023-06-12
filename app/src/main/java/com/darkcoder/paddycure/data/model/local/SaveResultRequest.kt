package com.darkcoder.paddycure.data.model.local

data class SaveResultRequest(
    val user_id: String,
    val penyakit: String,
    val confidence: String,
    val suggesion: String,
    val deskripsiPenyakit: String,
)

