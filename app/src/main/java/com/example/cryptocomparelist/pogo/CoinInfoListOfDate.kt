package com.example.cryptocomparelist.pogo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class CoinInfoListOfDate (
    @SerializedName("Data")
    @Expose
   val data: List<Datum>? = null
)