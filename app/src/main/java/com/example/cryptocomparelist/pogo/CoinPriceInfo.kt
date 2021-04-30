package com.example.cryptocomparelist.pogo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptocomparelist.api.ApiFactory.BASE_IMAGE_URL
import com.example.cryptocomparelist.utils.TimeUtils
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



@Entity(tableName = "full_price_list",)
data class CoinPriceInfo (
    @SerializedName("TYPE")
    @Expose
     val type: String?,

    @SerializedName("MARKET")
    @Expose
     val market: String?,

    @SerializedName("FROMSYMBOL")
    @Expose
    @PrimaryKey
     var fromSymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
    val tosymbol: String?,

    @SerializedName("FLAGS")
    @Expose
     val flags: String?,

    @SerializedName("PRICE")
    @Expose
    val price: Double?,

    @SerializedName("LASTUPDATE")
    @Expose
    val lastupdate: Long?,

    @SerializedName("MEDIAN")
    @Expose
     val median: Double?,

    @SerializedName("LASTVOLUME")
    @Expose
     val lastvolume: Double?,

    @SerializedName("LASTVOLUMETO")
    @Expose
   val lastvolumeto: Double?,

    @SerializedName("LASTTRADEID")
    @Expose
    val lasttradeid: String?,

    @SerializedName("VOLUMEDAY")
    @Expose
    val volumeday: Double?,

    @SerializedName("VOLUMEDAYTO")
    @Expose
     val volumedayto: Double?,

    @SerializedName("VOLUME24HOUR")
    @Expose
     val volume24hour: Double?,

    @SerializedName("VOLUME24HOURTO")
    @Expose
     val volume24hourto: Double?,

    @SerializedName("OPENDAY")
    @Expose
     val openday: Double?,

    @SerializedName("HIGHDAY")
    @Expose
     val highday: Double?,

    @SerializedName("LOWDAY")
    @Expose
     val lowday: Double?,

    @SerializedName("OPEN24HOUR")
    @Expose
     val open24hour: Double?,
    @SerializedName("HIGH24HOUR")
    @Expose
     val high24hour: Double?,

    @SerializedName("LOW24HOUR")
    @Expose
     val low24hour: Double?,

    @SerializedName("IMAGEURL")
    @Expose
    val imageurl: String?,


    )
{

    fun getFormattedTime():String{

        val a = TimeUtils()
        return  a.convertTimestamtToTime(lastupdate)


    }
    fun getFullImageURl():String{
        return BASE_IMAGE_URL + imageurl

    }
}