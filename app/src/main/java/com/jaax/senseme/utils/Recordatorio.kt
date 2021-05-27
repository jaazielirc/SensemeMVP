package com.jaax.senseme.utils

import com.google.gson.annotations.SerializedName

data class Recordatorio(
    @SerializedName( value = "id" )
    val id: Int = 0,
    @SerializedName( value = "text" )
    var text: String = "",
    @SerializedName( value = "date" )
    var date: String = ""
)