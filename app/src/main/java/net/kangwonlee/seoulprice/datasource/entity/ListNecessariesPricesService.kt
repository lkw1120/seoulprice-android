package net.kangwonlee.seoulprice.datasource.entity

import com.google.gson.annotations.SerializedName

data class ListNecessariesPricesService (

	@SerializedName("list_total_count")
	val listTotalCount : Int,
	@SerializedName("RESULT")
	val result : Result,
	@SerializedName("row")
	val row : List<Row>
)