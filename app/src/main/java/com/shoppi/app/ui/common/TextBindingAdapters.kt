package com.shoppi.app.ui.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.shoppi.app.R
import java.text.DecimalFormat
import kotlin.math.roundToInt

// 3자리수마다 comma 찍기
@BindingAdapter("priceAmount")
fun applyPriceFormat(view:TextView, price:Int){
    val decimalFormat = DecimalFormat("#,###")
    view.context.getString(R.string.unit_discount_currency,  decimalFormat.format(price))
}

// 계산하기
@BindingAdapter("priceAmount", "discountRate") // 2개가 동시에 선언됐을때 사용가능.
fun applyPriceDiscountRate(view: TextView, price: Int, discountRate: Int){
    val discountPrice = ((100- discountRate) / 100.0 * price).roundToInt()
    applyPriceFormat(view, discountPrice)
}