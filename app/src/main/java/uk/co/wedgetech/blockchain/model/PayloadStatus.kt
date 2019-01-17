package uk.co.wedgetech.blockchain.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class PayloadStatus(val timestamp: Date,
                         @SerializedName("error_code") val errorCode: Int,
                         @SerializedName("error_message") val errorMessage: String)