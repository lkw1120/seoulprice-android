package net.kangwonlee.seoulprice.datasource.entity

import com.google.gson.annotations.SerializedName

data class ApiResp (
	@SerializedName("ListNecessariesPricesService")
	val listNecessariesPricesService : ListNecessariesPricesService
)