package com.damirmustafin.cryptocurrency.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.damirmustafin.cryptocurrency.R
import com.damirmustafin.cryptocurrency.viewmodel.Api.CoinViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail.*

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if(!intent.hasExtra(EXTRA_FROM_SYMBOL)){
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        if (fromSymbol != null) {
            viewModel.getDetailedInfo(fromSymbol).observe(this,Observer{
                with(it) {
                    textViewPrice.text = price.toString()
                    textViewMin.text = lowday.toString()
                    textViewMax.text = highday.toString()
                    textViewCompany.text = market.toString()
                    textViewTime.text = getFormatedTime()
                    textViewBTC.text = fromsymbol
                    textviewUSD.text = tosymbol
                    Picasso.get().load(getFullImageURL()).into(imageView2)
                }
            })
        }
    }

    companion object{
       private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol:String):Intent{
        val intent = Intent(context,CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL,fromSymbol)
            return intent
        }
    }
}