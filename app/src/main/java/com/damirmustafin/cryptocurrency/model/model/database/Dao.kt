package com.damirmustafin.cryptocurrency.model.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.damirmustafin.cryptocurrency.viewmodel.Api.pojo.CoinInfo
import com.damirmustafin.cryptocurrency.viewmodel.Api.pojo.CoinPriceInfo

@Dao
interface CoinPriceInfoDao {
    @Query("SELECT * FROM Full_Price_List ORDER BY lastUpdate DESC")
    fun GetPriceList():LiveData<List<CoinPriceInfo>>

    @Query("SELECT * FROM full_price_list WHERE fromSymbol == :fSym LIMIT 1")
    fun getPriceInfoAboutCoin(fSym:String): LiveData<CoinPriceInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList:List<CoinPriceInfo>)
}