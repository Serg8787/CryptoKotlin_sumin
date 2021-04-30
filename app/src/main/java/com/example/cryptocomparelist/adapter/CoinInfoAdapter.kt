package com.example.cryptocomparelist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocomparelist.R
import com.example.cryptocomparelist.pogo.CoinInfo
import com.example.cryptocomparelist.pogo.CoinPriceInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_info.view.*

class CoinInfoAdapter(private var context: Context): RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {
    var onCoinClickListener:OnCoinClickListener? = null

    var coinPriceInfoList:List<CoinPriceInfo> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }



    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageViewIcon = itemView.imageViewIcon
        val txLastUpdate = itemView.txLastUpdate
        val txPrice = itemView.txPrice
        val txSymbols = itemView.txSymbols

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info,parent,false)
        return CoinInfoViewHolder(view)

    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinPriceInfoList[position]
      with(holder) {
       txSymbols.text = coin.fromSymbol + "/" + coin.tosymbol
         txPrice.text = coin.price.toString()
         txLastUpdate.text =context.getString(R.string.last_update) + coin.getFormattedTime()
          Picasso.get().load(coin.getFullImageURl()).into(imageViewIcon)

          itemView.setOnClickListener { onCoinClickListener?.onCoinClic(coin) }
      }



    }

    override fun getItemCount(): Int {
        return coinPriceInfoList.size
    }
    interface OnCoinClickListener{
        fun onCoinClic(coinPriceInfo:CoinPriceInfo)
    }
}