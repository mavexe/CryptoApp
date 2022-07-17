package com.damirmustafin.cryptocurrency.viewmodel.Api

import com.damirmustafin.cryptocurrency.viewmodel.Api.pojo.CoinInfoListOfData
import com.damirmustafin.cryptocurrency.viewmodel.Api.pojo.CoinPriceInfoRawData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface apiService {

    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey:String = "",
        @Query(QUERY_PARAM_LIMIT) limit:Int = 10,
    @Query(QUERY_PARAM_TO_SYMBOL) tsym:String = CURRENCY
    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey:String = "f9f772dca35bccb85693346e35d4a91e319bbd1028f4f71f77b44e9217935eec",
        @Query(QUERY_PARAM_TO_SYMBOLS) tsyms:String = CURRENCY,
    @Query(QUERY_PARAM_FROM_SYMBOLS) fsyms:String):Single<CoinPriceInfoRawData>


    companion object{
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_API_KEY = "api_key"

        private const val QUERY_PARAM_TO_SYMBOLS = "tsym"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsym"
       private const val CURRENCY = "USD"
    }
}