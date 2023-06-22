package com.shoppi.app.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

// databinding의 커스텀 속성
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl:String?) // Glide Library를 이용해 이미지 출력
{
    if(!imageUrl.isNullOrEmpty()){
        Glide
            .with(view)
            .load(imageUrl)
            .into(view)
    }
}