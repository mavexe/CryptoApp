package com.damirmustafin.cryptocurrency.viewmodel.Api.pojo

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



@Entity(tableName = "Full_Price_List")
data class CoinPriceInfo (
    @SerializedName("TYPE")
    @Expose
   val type: String? = null,

    @SerializedName("MARKET")
    @Expose
     val market: String? = null,

    @PrimaryKey
    @NonNull
    @SerializedName("FROMSYMBOL")
    @Expose
     val fromsymbol: String? = null,

    @SerializedName("TOSYMBOL")
    @Expose
     val tosymbol: String? = null,

    @SerializedName("FLAGS")
    @Expose
     val flags: String? = null,

    @SerializedName("PRICE")
    @Expose
     val price: Double? = null,

    @SerializedName("LASTUPDATE")
    @Expose
     val lastupdate: Int? = null,

    @SerializedName("MEDIAN")
    @Expose
     val median: Double? = null,

    @SerializedName("LASTVOLUME")
    @Expose
     val lastvolume: Double? = null,

    @SerializedName("LASTVOLUMETO")
    @Expose
     val lastvolumeto: Double? = null,

    @SerializedName("LASTTRADEID")
    @Expose
     val lasttradeid: String? = null,

    @SerializedName("VOLUMEDAY")
    @Expose
     val volumeday: Double? = null,

    @SerializedName("VOLUMEDAYTO")
    @Expose
     val volumedayto: Double? = null,

    @SerializedName("VOLUME24HOUR")
    @Expose
     val volume24hour: Double? = null,

    @SerializedName("VOLUME24HOURTO")
    @Expose
     val volume24hourto: Double? = null,

    @SerializedName("OPENDAY")
    @Expose
     val openday: Double? = null,

    @SerializedName("HIGHDAY")
    @Expose
     val highday: Double? = null,

    @SerializedName("LOWDAY")
    @Expose
     val lowday: Double? = null,


    @SerializedName("IMAGEURL")
    @Expose
     val imageurl: String? = null
    )