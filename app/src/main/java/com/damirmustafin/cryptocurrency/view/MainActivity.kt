package com.damirmustafin.cryptocurrency.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.damirmustafin.cryptocurrency.R
import com.damirmustafin.cryptocurrency.viewmodel.Api.CoinViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
    }

}

