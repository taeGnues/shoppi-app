package com.shoppi.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.gson.Gson

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewpager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
        val viewpagerIndicator = view.findViewById<TableLayout>(R.id.viewpager_home_banner_indicator)

        val assetLoader = AssetLoader()
        val homeJsonString = assetLoader.getJsonString(requireContext(), "home.json")
// Asset에서 가져온 Json 파일에서 string을 가져옴.
        if (!homeJsonString.isNullOrEmpty()) { // homeData가 null일 수 있음.
            val gson = Gson()
            val homeData = gson.fromJson(homeJsonString, HomeData::class.java)
// Gson 객체를 선언해주고 Gson의 fromJson 메소드에 Json string과 변환할 java 클래스를 인수로 넘김


            val toolbarTitle = view.findViewById<TextView>(R.id.tv_toolbar_home_title)
            val toolbarIcon = view.findViewById<ImageView>(R.id.iv_toolbar_home_icon)
            toolbarTitle.text = homeData.title.text

            Glide
                .with(this)
                .load(homeData.title.iconUrl)
                .into(toolbarIcon);

            viewpager.adapter = HomeBannerAdapter().apply {
                submitList(homeData.topBanners)
            } // 인스턴스 생성과 동시에 데이터 전달
        }
    }}