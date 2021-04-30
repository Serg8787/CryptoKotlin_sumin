package com.example.cryptocomparelist.api

import com.example.cryptocomparelist.pogo.CoinInfoListOfDate
import com.example.cryptocomparelist.pogo.CoinPriceInfoRawData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("top/totalvolfull")
    fun getTopCoinInfo(
        @Query(QUERY_PARAMS_API_KEY) apikey: String = "a31acf3b37b8d81f3ada6efdb38d9361d6b66ca22f4de335abf6bf2591401c63",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 20,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY

    ): Single<CoinInfoListOfDate>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAMS_API_KEY) apikey: String = "a31acf3b37b8d81f3ada6efdb38d9361d6b66ca22f4de335abf6bf2591401c63",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
    ): Single<CoinPriceInfoRawData>


    companion object {
        private const val QUERY_PARAMS_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
    }
}