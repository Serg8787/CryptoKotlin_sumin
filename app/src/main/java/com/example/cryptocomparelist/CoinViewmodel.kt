package com.example.cryptocomparelist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptocomparelist.api.ApiFactory
import com.example.cryptocomparelist.database.AppDatabase
import com.example.cryptocomparelist.pogo.CoinPriceInfo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.concurrent.TimeUnit

@InternalCoroutinesApi
class CoinViewmodel(application: Application) : AndroidViewModel(application) {
    @InternalCoroutinesApi
    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList()
    init{
        loadData()
    }

    fun getDeteillInfo(fSym: String?):LiveData<CoinPriceInfo> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

    private fun loadData(){
        val disposable = ApiFactory.apiService.getTopCoinInfo(limit = 15)
            .map {  it.data?.map{it.coinInfo?.name}?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
            .map { it.coinPriceInfoHashMap?.map { it.value }?.flatMap { it.values } }
            .delaySubscription(10,TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())

            .subscribe({

                if (it != null) {
                    db.coinPriceInfoDao().insertPriceList(it)
                }
                    Log.i("textt", it.toString())

            },{
                Log.i("text", it.message.toString())

            })
        compositeDisposable.add(disposable)

    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}