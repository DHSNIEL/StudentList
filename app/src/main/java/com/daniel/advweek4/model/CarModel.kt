package com.daniel.advweek4.model

import com.google.gson.annotations.SerializedName

data class Car(
    val id:Int?,
    val make: String?,
    val model: String?,
    val year: Int?,
    val colors: ArrayList<String>?,
    val features: Features?,
    val images: String?
)

data class Features(
    val engine: String?,
    val transmission: String?,
    val interior: String?
)