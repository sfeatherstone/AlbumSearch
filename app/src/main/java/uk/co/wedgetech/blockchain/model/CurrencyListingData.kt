package uk.co.wedgetech.blockchain.model

import com.google.gson.annotations.SerializedName

data class CurrencyListingData(val id: Int,
                               @SerializedName("cmc_rank") val rank: Int,
                               val name:String,
                               @SerializedName("circulating_supply") val circulatingSupply: String,
                               @SerializedName("max_supply") val maxSupply :String?,
                               @SerializedName("quote") val quote:CurrencyQuote)