package com.damirmustafin.cryptocurrency.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.damirmustafin.cryptocurrency.R
import com.damirmustafin.cryptocurrency.viewmodel.Api.pojo.CoinInfo
import com.damirmustafin.cryptocurrency.viewmodel.Api.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_info.view.*

class coinInfoAdapter: RecyclerView.Adapter<coinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList:List<CoinPriceInfo> = arrayListOf<CoinPriceInfo>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    inner class CoinInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageViewLogoCoin = itemView.imageView
        val tvSymbols = itemView.txViewSymbols
        val tvPrice = itemView.txViewCounts
        val tvTime = itemView.txViewTime
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
     val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info,parent,false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
       val coin = coinInfoList[position]
        with(holder){
            with(coin){
        tvSymbols.text = fromsymbol+"/"+coin.tosymbol
       tvPrice.text = price.toString()
       tvTime.text = getFormatedTime()
       Picasso.get().load(getFullImageURL()).into(imageViewLogoCoin)}}
    }

    override fun getItemCount() = coinInfoList.size
}