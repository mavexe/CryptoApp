package com.damirmustafin.cryptocurrency.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.damirmustafin.cryptocurrency.R
import com.damirmustafin.cryptocurrency.databinding.ActivityCoinPriceListBinding
import com.damirmustafin.cryptocurrency.viewmodel.Api.CoinViewModel
import kotlinx.android.synthetic.main.activity_coin_price_list.*


class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel:CoinViewModel
    private lateinit var binding:ActivityCoinPriceListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater).also {setContentView(it.root)}
        val adapter = coinInfoAdapter()
        rcViewCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
       viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })
    }

}

