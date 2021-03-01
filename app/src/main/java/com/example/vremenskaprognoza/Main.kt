package com.example.vremenskaprognoza

import com.google.gson.annotations.SerializedName

class Main (
    @SerializedName("temp")
    var temp:Double,
    @SerializedName("humidity")
    var humidity: Double,
    @SerializedName("pressure")
    var pressure: Double,
    @SerializedName("temp_min")
    var temp_min: Double,
    @SerializedName("temp_max")
    var temp_max: Double


)
