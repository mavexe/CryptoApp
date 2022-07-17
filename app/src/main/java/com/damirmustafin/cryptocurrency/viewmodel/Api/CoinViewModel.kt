package com.damirmustafin.cryptocurrency.viewmodel.Api

import android.app.Application
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.damirmustafin.cryptocurrency.model.model.database.AppDatabase
import com.damirmustafin.cryptocurrency.viewmodel.Api.pojo.CoinPriceInfo
import com.damirmustafin.cryptocurrency.viewmodel.Api.pojo.CoinPriceInfoRawData
import com.google.gson.Gson
import com.google.gson.JsonElement
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().GetPriceList()

    init {
        LoadData()
    }
    fun LoadData() {
        val disposable = apiFactory.ApiService.getTopCoinsInfo()
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
            .flatMap { apiFactory.ApiService.getFullPriceList(fsyms = it) }
            .map{getPriceListFromRawData(it)}
            .delaySubscription(10,TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    db.coinPriceInfoDao().insertPriceList(it)
                }
                Log.d("TEST_DATA", it.toString())

            }, {
                Log.d("TEST_DATA", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(
        coinPriceInfoRawData:
        CoinPriceInfoRawData
    ): List<CoinPriceInfo>? {
        val jsOnObject = coinPriceInfoRawData.coinPriceInfoJSONObject
        val result = ArrayList<CoinPriceInfo>()
        if (jsOnObject == null) return result
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

    fun getDetailedInfo(fSym:String):LiveData<CoinPriceInfo>{
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}