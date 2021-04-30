package com.example.cryptocomparelist.pogo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class CoinPriceInfoRawData (
    @SerializedName("RAW")
    @Expose
    val coinPriceInfoHashMap: HashMap<String, HashMap<String, CoinPriceInfo>>? = null

        )