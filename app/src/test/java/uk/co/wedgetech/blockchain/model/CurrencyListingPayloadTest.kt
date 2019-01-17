package uk.co.wedgetech.blockchain.model

import org.junit.Assert.*
import org.junit.Test
import com.google.gson.Gson
import org.junit.Assert
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.InputStream
import java.nio.charset.Charset
import java.util.*


class CurrencyListingPayloadTest{

    @Test
    fun payloadHappyPath() {
        val json = loadJson("test_input.json")
        val targetObject = Gson().fromJson(json, CurrencyListingPayload::class.java)
        Assert.assertEquals(targetObject.status.timestamp, Date(1547674049512))
        Assert.assertEquals(targetObject.status.errorCode, 0)
        Assert.assertNull(targetObject.status.errorMessage)


        Assert.assertEquals(targetObject.data.size, 50)
        Assert.assertEquals(targetObject.data[0] , CurrencyListingData(1, 1, "Bitcoin",
            "17435912", "21000000",
            CurrencyQuote("GBP", "3072.961280701185", "5020390056.119624","53579882469.71316")))

        Assert.assertEquals(targetObject.data[37] , CurrencyListingData(1567, 38, "Nano",
            "133248289.1965", "133248290",
            CurrencyQuote("GBP", "0.7853266777248444", "2360439.5182599076","104643436.26720662")))

    }

    internal fun loadJson(filename: String): String {
        return javaClass.classLoader?.getResourceAsStream(filename)?.readTextAndClose() ?:""
    }

}

fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String {
    return this.bufferedReader(charset).use { it.readText() }
}
