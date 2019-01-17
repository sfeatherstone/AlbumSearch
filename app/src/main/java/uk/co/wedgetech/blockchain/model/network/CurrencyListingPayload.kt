package uk.co.wedgetech.blockchain.model.network

data class CurrencyListingPayload (val status: PayloadStatus, val data: List<CurrencyListingData>?)