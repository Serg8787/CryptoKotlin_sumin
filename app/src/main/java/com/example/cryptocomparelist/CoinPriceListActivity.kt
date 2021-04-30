package com.example.cryptocomparelist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cryptocomparelist.adapter.CoinInfoAdapter
import com.example.cryptocomparelist.pogo.CoinPriceInfo
import kotlinx.android.synthetic.main.activity_coin_price_list.*
import kotlinx.coroutines.InternalCoroutinesApi

class CoinPriceListActivity : AppCompatActivity() {
    @InternalCoroutinesApi
    private lateinit var viewModel: CoinViewmodel



    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
       val adaptep = CoinInfoAdapter(this)
        recyclerCoinPriceList.adapter = adaptep
        adaptep.onCoinClickListener = object :CoinInfoAdapter.OnCoinClickListener{
            override fun onCoinClic(coinPriceInfo: CoinPriceInfo) {
              val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity,coinPriceInfo.fromSymbol )
                startActivity(intent)
            }

        }


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[CoinViewmodel::class.java]

        viewModel.priceList.observe(this, Observer {
            adaptep.coinPriceInfoList = it
        })


    }
}