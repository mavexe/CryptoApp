package com.damirmustafin.cryptocurrency.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.damirmustafin.cryptocurrency.R
import com.damirmustafin.cryptocurrency.viewmodel.Api.pojo.CoinInfo
import com.damirmustafin.cryptocurrency.viewmodel.Api.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_info.view.*

class coinInfoAdapter(private var context:Context): RecyclerView.Adapter<coinInfoAdapter.CoinInfoViewHolder>() {

    var OnCoinClickListener: onCoinClickListener? = null
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
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.time_template)
        tvSymbols.text = String.format(symbolsTemplate,fromsymbol,tosymbol)
       tvPrice.text = price.toString()
       tvTime.text = String.format(lastUpdateTemplate,getFormatedTime())
       Picasso.get().load(getFullImageURL()).into(imageViewLogoCoin)}}

        holder.itemView.setOnClickListener{
            OnCoinClickListener?.onCoinClick(coin)
        }
    }

    override fun getItemCount() = coinInfoList.size

    interface onCoinClickListener{
        fun onCoinClick(coinPriceInfo:CoinPriceInfo){}
    }
}