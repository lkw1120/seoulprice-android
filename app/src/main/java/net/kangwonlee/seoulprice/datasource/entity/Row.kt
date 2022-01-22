package net.kangwonlee.seoulprice.datasource.entity

import com.google.gson.annotations.SerializedName

data class Row (

	@SerializedName("P_SEQ")
	val pSeq : Int,
	@SerializedName("M_SEQ")
	val mSeq : Int,
	@SerializedName("M_NAME")
	val mName : String,
	@SerializedName("A_SEQ")
	val aSeq : Int,
	@SerializedName("A_NAME")
	val aName : String,
	@SerializedName("A_UNIT")
	val aUnit : String,
	@SerializedName("A_PRICE")
	val aPrice : Int,
	@SerializedName("P_YEAR_MONTH")
	val pYearMonth : String,
	@SerializedName("ADD_COL")
	val addCol : String,
	@SerializedName("P_DATE")
	val pDate : String,
	@SerializedName("M_TYPE_CODE")
	val mTypeCode : Int,
	@SerializedName("M_TYPE_NAME")
	val mTypeName : String,
	@SerializedName("M_GU_CODE")
	val mGuCode : Int,
	@SerializedName("M_GU_NAME")
	val mGuName : String
)