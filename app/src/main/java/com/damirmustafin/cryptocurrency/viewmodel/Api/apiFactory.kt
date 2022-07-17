package com.damirmustafin.cryptocurrency.viewmodel.Api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object apiFactory {

    private const val BASE_URL = "https://min-api.cryptocompare.com/data/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val ApiService = retrofit.create(apiService::class.java)
}