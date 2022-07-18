package com.damirmustafin.cryptocurrency.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.damirmustafin.cryptocurrency.R
import com.damirmustafin.cryptocurrency.databinding.ActivityCoinPriceListBinding
import com.damirmustafin.cryptocurrency.viewmodel.Api.CoinViewModel
import com.damirmustafin.cryptocurrency.viewmodel.Api.pojo.CoinPriceInfo
import kotlinx.android.synthetic.main.activity_coin_price_list.*


class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel:CoinViewModel
    private lateinit var binding:ActivityCoinPriceListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater).also {setContentView(it.root)}
        val adapter = coinInfoAdapter(this)
        adapter.OnCoinClickListener = object : coinInfoAdapter.onCoinClickListener{
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                super.onCoinClick(coinPriceInfo)
                val intent = CoinDetailActivity
                    .newIntent(this@CoinPriceListActivity,coinPriceInfo.fromsymbol)
            }
        }
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
       viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })
    }

}

