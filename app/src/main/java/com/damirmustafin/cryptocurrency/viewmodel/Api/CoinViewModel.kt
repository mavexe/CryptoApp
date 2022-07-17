package com.damirmustafin.cryptocurrency.viewmodel.Api

import android.app.Application
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.damirmustafin.cryptocurrency.model.model.database.AppDatabase
import com.damirmustafin.cryptocurrency.viewmodel.Api.pojo.CoinPriceInfo
import com.damirmustafin.cryptocurrency.viewmodel.Api.pojo.CoinPriceInfoRawData
import com.google.gson.Gson
import com.google.gson.JsonElement
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().GetPriceList()

    fun LoadData() {
        val disposable = apiFactory.ApiService.getTopCoinsInfo()
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
            .flatMap { apiFactory.ApiService.getFullPriceList(fsyms = it) }
            .map{getPriceListFromRawData(it)}
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TEST_DATA", it.toString())

            }, {
                Log.d("TEST_DATA", it.message.toString())
            })
    }

    private fun getPriceListFromRawData(
        coinPriceInfoRawData:
        CoinPriceInfoRawData
    ): List<CoinPriceInfo>? {
        val jsOnObject = coinPriceInfoRawData.coinPriceInfoJSONObject
        val result = ArrayList<CoinPriceInfo>()
        if (jsOnObject == null) return null
        val CoinKeySet = jsOnObject.keys()
        for (coinKey in CoinKeySet) {
            val currencyJson = jsOnObject.getJSONObject(coinKey)
            val currencyKeySet = currencyJson.keys()
            for (currency in currencyKeySet) {
                var priceInfo: CoinPriceInfo = Gson().fromJson<CoinPriceInfo>(
                    currencyJson.getJSONObject(currency) as JsonElement,
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}