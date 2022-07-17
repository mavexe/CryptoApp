package com.damirmustafin.cryptocurrency.viewmodel.Api.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class CoinPriceInfoRawData (
    @SerializedName("RAW")
    @Expose
    val coinPriceInfoJSONObject: JSONObject? = null
        )