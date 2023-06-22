package com.shoppi.app.ui.common

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.databinding.BindingConversion

@BindingConversion // color code값이 문자열로 들어왔을때 전환
fun convertToColorDrawable(color: String): Drawable{
    return ColorDrawable(Color.parseColor(color))
}