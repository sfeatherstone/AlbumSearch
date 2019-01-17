package uk.co.wedgetech.blockchain.model

import com.google.gson.JsonParseException
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonDeserializer
import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type



@JsonAdapter(CurrencyQuote.DateTimeDeserializer::class)
data class CurrencyQuote(val currency:String, val price: String, val volume24h: String, val marketCap: String) {

    class DateTimeDeserializer : JsonDeserializer<CurrencyQuote> {
        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): CurrencyQuote? {
            return json.asJsonObject?.let() {
                val currency = it?.keySet()?.iterator()?.next()
                currency?.let() { currencyName ->
                    val innerObject = it[currency].asJsonObject
                    CurrencyQuote(currencyName,
                        innerObject["price"].asString,
                        innerObject["volume_24h"].asString,
                        innerObject["market_cap"].asString)
                }
            }
        }
    }
}

