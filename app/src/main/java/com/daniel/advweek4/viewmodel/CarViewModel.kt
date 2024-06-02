package com.daniel.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.daniel.advweek4.model.Car
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

class CarViewModel(application: Application): AndroidViewModel(application)  {
    val carsLD = MutableLiveData<ArrayList<Car>>()
    val carLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        loadingLD.value = true
        carLoadErrorLD.value = false
        try {
            val inputStream = getApplication<Application>().assets.open("car.json")
            val reader = BufferedReader(InputStreamReader(inputStream))
            val jsonString = reader.use { it.readText() }

            val carListType = object : TypeToken<List<Car>>() {}.type
            val cars: List<Car> = Gson().fromJson(jsonString, carListType)
            carsLD.value = ArrayList(cars)

            loadingLD.value = false
        } catch (exception: Exception) {
            Log.e("CarViewModel", "Error reading JSON", exception)
            carLoadErrorLD.value = true
            loadingLD.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}