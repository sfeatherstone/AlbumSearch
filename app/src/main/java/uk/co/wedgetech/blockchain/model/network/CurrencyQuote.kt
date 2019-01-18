package uk.co.wedgetech.blockchain.model.network

import android.util.Log
import com.google.gson.JsonParseException
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonDeserializer
import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type

@JsonAdapter(CurrencyQuote.DateTimeDeserializer::class)
data class CurrencyQuote(val currency:String, val price: String, val volume24h: String?, val marketCap: String) {

    //Inner class to deal with JSON deserialation
    class DateTimeDeserializer : JsonDeserializer<CurrencyQuote> {
        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): CurrencyQuote? {
            return json.asJsonObject?.let {
                val currency = it.keySet()?.iterator()?.next()
                currency?.let { currencyName ->
                    val innerObject = it[currency].asJsonObject
                    val volume24h = innerObject.get("volume_24h")
                    val volume24hAsString = if (volume24h.isJsonNull) "" else volume24h.asString
                    CurrencyQuote(
                        currencyName,
                        innerObject["price"].asString,
                        volume24hAsString ,
                        innerObject["market_cap"].asString
                    )
                }
            }
        }
    }
}

