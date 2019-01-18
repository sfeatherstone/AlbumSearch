package uk.co.wedgetech.blockchain.model

data class Currency(val id: Int,
                    val rank: Int,
                    val name:String,
                    val circulatingSupply: String?,
                    val maxSupply :String?,
                    val currency:String,
                    val price: String,
                    val volume24h: String?,
                    val marketCap: String)