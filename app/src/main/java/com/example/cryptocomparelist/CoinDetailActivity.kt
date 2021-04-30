package com.example.cryptocomparelist

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail.*
import kotlinx.coroutines.InternalCoroutinesApi

class CoinDetailActivity : AppCompatActivity() {
    @InternalCoroutinesApi
    private lateinit var viewModel: CoinViewmodel

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if(!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
            val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[CoinViewmodel::class.java]



            viewModel.getDeteillInfo(fromSymbol).observe(this, Observer {
                tvPrice.text = it.price.toString()
                tvMinPrice.text =it.low24hour.toString()
                tvMaxPrice.text = it.high24hour.toString()
                tvLastMarket.text = it.lasttradeid
                tvLastUpdate.text = it.getFormattedTime()
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.tosymbol
                Picasso.get().load(it.getFullImageURl()).into(ivLogoCoin)

            })

    }
    companion object{
        private const val EXTRA_FROM_SYMBOL = "fSym"
        fun newIntent(context: Context, fromSymbol:String) : Intent {

            val intent = Intent(context,CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL,fromSymbol)
            return intent

        }
    }
}