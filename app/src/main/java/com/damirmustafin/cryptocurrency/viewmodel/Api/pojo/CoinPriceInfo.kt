package com.damirmustafin.cryptocurrency.viewmodel.Api.pojo

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.damirmustafin.cryptocurrency.viewmodel.Utils.convertTimeStampToTime
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


@Entity(tableName = "Full_Price_List")
data class CoinPriceInfo (
    @SerializedName("TYPE")
    @Expose
   val type: String?,

    @SerializedName("MARKET")
    @Expose
     val market: String?,
    @PrimaryKey
    @NonNull
    @SerializedName("FROMSYMBOL")
    @Expose
     val fromsymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
     val tosymbol: String?,

    @SerializedName("FLAGS")
    @Expose
     val flags: String?,

    @SerializedName("PRICE")
    @Expose
     val price: Double?,

    @SerializedName("MEDIAN")
    @Expose
     val median: Double?,

    @SerializedName("LASTVOLUME")
    @Expose
     val lastvolume: Double?,

    @SerializedName("LASTVOLUMETO")
    @Expose
     val lastvolumeto: Double?,

    @SerializedName("LASTTRADEID")
    @Expose
     val lasttradeid: String?,

    @SerializedName("VOLUMEDAY")
    @Expose
     val volumeday: Double?,

    @SerializedName("VOLUMEDAYTO")
    @Expose
     val volumedayto: Double?,

    @SerializedName("VOLUME24HOUR")
    @Expose
     val volume24hour: Double?,

    @SerializedName("VOLUME24HOURTO")
    @Expose
     val volume24hourto: Double?,

    @SerializedName("OPENDAY")
    @Expose
     val openday: Double?,

    @SerializedName("HIGHDAY")
    @Expose
     val highday: Double?,

    @SerializedName("LOWDAY")
    @Expose
     val lowday: Double?,


    @SerializedName("IMAGEURL")
    @Expose
     val imageurl: String?,

    @SerializedName("LASTUPDATE")
    @Expose
   var lastUpdate: Long?
){
 fun getFormatedTime():String{
  return convertTimeStampToTime(lastUpdate)
 }

 fun getFullImageURL(): String {
  val a = String
  if(imageurl != null) {val a = "https://cryptocompare.com${imageurl}"}
  return a.toString()
}
}
