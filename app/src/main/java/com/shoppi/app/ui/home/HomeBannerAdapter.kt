package com.shoppi.app.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shoppi.app.Banner
import com.shoppi.app.R
import java.text.DecimalFormat
import kotlin.math.roundToInt

class HomeBannerAdapter :
    ListAdapter<Banner, HomeBannerAdapter.HomeBannerViewHolder>(BannerDiffCallback()) {
    class HomeBannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val bannerImageView = view.findViewById<ImageView>(R.id.iv_banner_image)
        private val bannerBadgeTextView = view.findViewById<TextView>(R.id.tv_banner_badge)
        private val bannerTitleTextView = view.findViewById<TextView>(R.id.tv_banner_title)
        private val bannerDetailThumbnailImageView =
            view.findViewById<ImageView>(R.id.iv_banner_detail_thumnail)
        private val bannerDetailBrandLabelTextView =
            view.findViewById<TextView>(R.id.tv_banner_detail_brand_label)
        private val bannerDetailProductLabelTextView =
            view.findViewById<TextView>(R.id.tv_banner_detail_product_label)
        private val bannerDetailProductDiscountRateTextView =
            view.findViewById<TextView>(R.id.tv_banner_detail_product_discount_rate)
        private val bannerDetailProductDiscountPriceTextView =
            view.findViewById<TextView>(R.id.tv_banner_detail_product_discount_price)
        private val bannerDetailProductPriceTextView =
            view.findViewById<TextView>(R.id.tv_banner_detail_product_price)

        fun bind(banner: Banner) {
            loadImage(banner.backgroundImageUrl, bannerImageView)
            bannerBadgeTextView.text = banner.badge.label
            bannerBadgeTextView.background = ColorDrawable(Color.parseColor(banner.badge.backgroundColor))
            bannerTitleTextView.text = banner.label
            loadImage(banner.productDetail.thumbnailImageUrl, bannerDetailThumbnailImageView)
            bannerDetailBrandLabelTextView.text = banner.productDetail.brandName
            bannerDetailProductLabelTextView.text = banner.productDetail.label
            bannerDetailProductDiscountRateTextView.text = "${banner.productDetail.discountRate}%"
            calculaterDiscountAmount(bannerDetailProductDiscountPriceTextView, banner.productDetail.discountRate, banner.productDetail.price)
            applyPriceFormat(bannerDetailProductPriceTextView, banner.productDetail.price)
        }

        private fun calculaterDiscountAmount(view: TextView, discountRate: Int, price: Int) {
            val discountPrice = ((100- discountRate) / 100.0 * price).roundToInt()
            applyPriceFormat(view, discountPrice)
        }

        private fun applyPriceFormat(view: TextView, price: Int){
            val decimalFormat = DecimalFormat("#,###")
            view.text = decimalFormat.format(price) + "원"
        }

        private fun loadImage(urlString: String, imageView: ImageView) {
            Glide.with(itemView)
                .load(urlString)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_banner, parent, false)

        return HomeBannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class BannerDiffCallback :
    DiffUtil.ItemCallback<Banner>() { // List Adapter는 DiffUtil.ItemCallback<T>를 인자로 받음.
    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem.productDetail.productId == newItem.productDetail.productId
    } // 2개의 아이디가 만약 같다면 areContentsTheSame 메소드가 호출됨

    override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem == newItem
    } // Banner의 두 객체의 나머지 프로퍼티도 확인하여 다른 것이 하나라도 있다면 두 객체는 다른 것으로 판단하여, UI를 새로 그려줌.

}

