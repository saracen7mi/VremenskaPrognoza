package com.example.vremenskaprognoza

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var weatherData: TextView? = null
    var text1: TextView? = null
    var text2: TextView? = null
    var text3: TextView? = null
    var text4: TextView? = null
    var text5: TextView? = null
    var imageView: ImageView? = null
    var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherData = findViewById(R.id.textView)
        text1 = findViewById(R.id.text1)
        text2 = findViewById(R.id.text2)
        text3 = findViewById(R.id.text3)
        text4 = findViewById(R.id.text4)
        text5 = findViewById(R.id.textView5)
        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.ImageView1)
        findViewById<View>(R.id.botom).setOnClickListener { getCurrentData() }
    }

    fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val db = retrofit.create(ApiInterface::class.java)
        val vp = db.getCurrentWeatherData(lat, lon, AppId)
        vp.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>?,
                response: Response<WeatherResponse>?
            ) {

                val weatherResponse = response!!.body()!!

                text1!!.append(weatherResponse.sys!!.country!!)
                text2!!.append((weatherResponse.name).toString())
                text3!!.append((weatherResponse.main!!.temp - 273.15).toString())
                text4!!.append((weatherResponse.main!!.humidity).toString())
                text5!!.append((weatherResponse.weather[0].description.toString()))
                textView!!.append((weatherResponse.main!!.pressure).toString())
                Picasso.get()
                    .load("https://openweathermap.org/img/wn/" + weatherResponse.weather[0].icon + "@2x.png")
                    .into(imageView)

            }

            override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
                weatherData!!.text = t!!.message
            }

        })


//    fun convert(a: String, b: String, c: Double) = when {
//        a == "Celsius" && b == "Kelvin" -> c + 273.15
//        a == "Celsius" && b == "Fahrenheit" -> (c * 9 / 5) + 32
//        a == "Fahrenheit" && b == "Kelvin" -> (c + 459.67) * 5 / 9
//        a == "Fahrenheit" && b == "Celsius" -> (c - 32) * 5 / 9
//        a == "Kelvin" && b == "Celsius" -> c - 273.15
//        a == "Kelvin" && b == "Fahrenheit" -> c * 9 / 5 - 459.67
//        else -> 0.0
//    }
    }


    companion object {

        var BaseUrl = "http://api.openweathermap.org/"
        var AppId = "39035ee15e6d3f19a7ea7179c3d50337"
        var lat = "44.1800"
        var lon = "18.9418"
        var q = "Vlasenica "
    }
}